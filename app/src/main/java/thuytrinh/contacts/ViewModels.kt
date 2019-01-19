package thuytrinh.contacts

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModels(
  private val contentResolver: ContentResolver
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    val reactiveContentResolver = ReactiveContentResolver(contentResolver)
    return MainViewModel(
      contentResolver = reactiveContentResolver
    ) as T
  }
}
