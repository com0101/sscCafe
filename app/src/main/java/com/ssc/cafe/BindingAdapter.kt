package com.ssc.cafe

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ssc.cafe.`object`.CafeItem
import com.ssc.cafe.data.AddItem
import com.ssc.cafe.home.ItemAdapter
import com.ssc.cafe.home.OrderAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background))
            .into(imgView)
    }
}

@BindingAdapter("listItem")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CafeItem>?) {
    val adapter = recyclerView.adapter as ItemAdapter
    data?.let {
        adapter.submitList(it)
    }
}

@BindingAdapter("listOrder")
fun bindOrder(recyclerView: RecyclerView, data: List<AddItem>?) {
    val adapter = recyclerView.adapter as OrderAdapter
    data?.let {
        adapter.submitList(it)
    }
}
