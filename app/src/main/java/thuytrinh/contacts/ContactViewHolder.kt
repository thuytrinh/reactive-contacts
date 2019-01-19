package thuytrinh.contacts

import androidx.recyclerview.widget.RecyclerView
import thuytrinh.contacts.databinding.ContactBinding

class ContactViewHolder(
  private val binding: ContactBinding
) : RecyclerView.ViewHolder(binding.root) {
  fun bindTo(contact: Contact) {
    binding.viewModel = contact
    binding.executePendingBindings()
  }
}
