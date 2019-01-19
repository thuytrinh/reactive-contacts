package thuytrinh.contacts

import androidx.recyclerview.widget.DiffUtil

object ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
  override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
    return oldItem == newItem
  }
}
