package thuytrinh.contacts

import android.database.Cursor

fun <T> Cursor.map(f: (Cursor) -> T): List<T> {
  val items = mutableListOf<T>()
  use {
    while (!it.isClosed && it.moveToNext()) {
      items += f(it)
    }
  }
  return items.toList()
}
