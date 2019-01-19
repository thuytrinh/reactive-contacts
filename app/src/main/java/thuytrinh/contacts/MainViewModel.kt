package thuytrinh.contacts

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gojuno.koptional.None
import com.gojuno.koptional.Some

class MainViewModel(
  private val contentResolver: ReactiveContentResolver
) : ViewModel() {
  val contacts = MutableLiveData<List<Contact>>().also {
    it.value = emptyList()
  }

  fun loadContacts() {
    val disposable = contentResolver
      .query(
        contentUri = ContactsContract.Contacts.CONTENT_URI,
        projection = arrayOf(
          ContactsContract.Contacts._ID,
          ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
          ContactsContract.Contacts.STARRED
        )
      )
      .map {
        when (it) {
          is None -> emptyList()
          is Some -> it.value.map { x -> x.asContact() }
        }
      }
      .subscribe(contacts::postValue)
  }
}
