package thuytrinh.contacts

import android.database.Cursor
import android.provider.ContactsContract.Contacts
import java.util.*

data class Contact(
  val id: String = UUID.randomUUID().toString(),
  val name: String? = null,
  val isStarred: Boolean = false,
  val avatarUri: String? = null
)

fun Cursor.asContact(): Contact {
  val id: String = getString(getColumnIndex(Contacts._ID))
  val name: String? = getString(getColumnIndex(Contacts.DISPLAY_NAME))
  val isStarred = getInt(getColumnIndex(Contacts.STARRED)) == 1
  val photoUri: String? = getString(getColumnIndex(Contacts.PHOTO_URI))
  return Contact(
    id = id,
    name = name,
    isStarred = isStarred,
    avatarUri = photoUri
  )
}
