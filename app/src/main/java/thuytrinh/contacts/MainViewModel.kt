package thuytrinh.contacts

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gojuno.koptional.None
import com.gojuno.koptional.Some
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
  private val contentResolver: ReactiveContentResolver
) : ViewModel() {
  val contacts = MutableLiveData<List<Contact>>().also {
    it.value = emptyList()
  }
  private var disposable: Disposable? = null

  fun loadContacts() {
    disposable = contentResolver
      .query(contentUri = ContactsContract.Contacts.CONTENT_URI)
      .map {
        when (it) {
          is None -> emptyList()
          is Some -> it.value
            .map { x -> x.asContact() }
            // To put favorite contacts on top.
            .sortedByDescending { x -> x.isStarred }
        }
      }
      .subscribeOn(Schedulers.io())
      .subscribe(contacts::postValue)
  }

  override fun onCleared() {
    disposable?.dispose()
  }
}
