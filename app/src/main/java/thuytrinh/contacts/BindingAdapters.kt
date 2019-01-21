package thuytrinh.contacts

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcByUri")
fun ImageView.setSrcByUri(uriString: String?) {
  when (uriString) {
    null -> setImageDrawable(null)
    else -> {
      Glide.with(context)
        .load(Uri.parse(uriString))
        .into(this)
    }
  }
}
