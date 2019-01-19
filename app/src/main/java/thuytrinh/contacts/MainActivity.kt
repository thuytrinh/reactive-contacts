package thuytrinh.contacts

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import thuytrinh.contacts.databinding.MainBinding

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = DataBindingUtil.setContentView<MainBinding>(this, R.layout.main)

    val viewModel = ViewModelProviders.of(
      this,
      ViewModels(contentResolver = contentResolver)
    )[MainViewModel::class.java]
    binding.viewModel = viewModel

    Dexter.withActivity(this)
      .withPermission(Manifest.permission.READ_CONTACTS)
      .withListener(object : PermissionListener {
        override fun onPermissionGranted(response: PermissionGrantedResponse?) {
          viewModel.loadContacts()
        }

        override fun onPermissionRationaleShouldBeShown(
          permission: PermissionRequest?,
          token: PermissionToken?
        ) {
        }

        override fun onPermissionDenied(response: PermissionDeniedResponse?) {}
      })
      .check()

    val contactsAdapter = ContactsAdapter()
    binding.contactsView.layoutManager = LinearLayoutManager(this)
    binding.contactsView.adapter = contactsAdapter

    viewModel.contacts.observe(this, Observer { contacts ->
      contactsAdapter.submitList(contacts)
    })
  }
}
