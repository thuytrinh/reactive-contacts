package thuytrinh.contacts

import android.content.ContentResolver
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import com.gojuno.koptional.Optional
import com.gojuno.koptional.toOptional
import io.reactivex.Observable

typealias ContentHasChanged = Unit

class ReactiveContentResolver(
  private val contentResolver: ContentResolver
) {
  fun query(
    contentUri: Uri,
    projection: Array<String>
  ): Observable<Optional<Cursor>> {
    fun query() = Observable.fromCallable {
      val cursor = contentResolver.query(
        contentUri,
        projection,
        null,
        null,
        null
      )
      cursor.toOptional()
    }
    return observeContentChanges(contentUri)
      .flatMap { query() }
      .startWith(query())
  }

  private fun observeContentChanges(contentUri: Uri): Observable<ContentHasChanged> {
    return Observable.create { emitter ->
      val contentObserver = object : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
          emitter.onNext(ContentHasChanged)
        }
      }
      contentResolver.registerContentObserver(contentUri, true, contentObserver)
      emitter.setCancellable {
        contentResolver.unregisterContentObserver(contentObserver)
      }
    }
  }
}
