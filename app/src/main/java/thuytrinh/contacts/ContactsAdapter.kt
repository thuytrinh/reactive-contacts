package thuytrinh.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import thuytrinh.contacts.databinding.ContactBinding

class ContactsAdapter : ListAdapter<Contact, ContactViewHolder>(
  ContactDiffCallback
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
    val binding = ContactBinding.inflate(LayoutInflater.from(parent.context))
    return ContactViewHolder(binding = binding)
  }

  override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
    val item = getItem(position)
    holder.bindTo(item)
  }
}
