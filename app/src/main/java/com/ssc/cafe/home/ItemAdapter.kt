package com.ssc.cafe.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssc.cafe.`object`.CafeItem
import com.ssc.cafe.databinding.ItemCafeItemsBinding

class ItemAdapter(var onClickListener: OnClickListener, var viewModel: HomeViewModel):
    ListAdapter<CafeItem, ItemAdapter.ItemViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder (ItemCafeItemsBinding
            .inflate(LayoutInflater.from(parent.context ), parent, false))

    }

    companion object DiffCallback : DiffUtil.ItemCallback<CafeItem>() {
        override fun areItemsTheSame(oldItem: CafeItem, newItem: CafeItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CafeItem, newItem: CafeItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : CafeItem = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
            Log.i("Sophie_Color","$item")
        }

        holder.bind(item)
    }


    class ItemViewHolder(private var binding: ItemCafeItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item : CafeItem) {
            binding.item = item
            binding.executePendingBindings()
        }

    }

    class OnClickListener(val clickListener: (item : CafeItem) -> Unit) {
        fun onClick(item : CafeItem) = clickListener(item)
    }


}