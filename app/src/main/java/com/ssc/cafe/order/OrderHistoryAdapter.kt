package com.ssc.cafe.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssc.cafe.data.AddItem
import com.ssc.cafe.databinding.ItemCafeOrderBinding
import com.ssc.cafe.home.HomeViewModel

class OrderHistoryAdapter(var viewModel : HomeViewModel):
    ListAdapter<AddItem, OrderHistoryAdapter.OrderHistoryViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewHolder {
        return OrderHistoryViewHolder (
            ItemCafeOrderBinding
                .inflate(LayoutInflater.from(parent.context ), parent, false))
    }


    companion object DiffCallback : DiffUtil.ItemCallback<AddItem>() {
        override fun areItemsTheSame(oldItem: AddItem, newItem: AddItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AddItem, newItem: AddItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: OrderHistoryViewHolder, position: Int) {
        val add: AddItem = getItem(position)
        holder.bind(add,viewModel)
    }


    class OrderHistoryViewHolder(private var binding: ItemCafeOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(add: AddItem, viewModel : HomeViewModel) {
            binding.order = add
            binding.viewModel = viewModel

            binding.executePendingBindings()
        }

    }

}