package com.ssc.cafe.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssc.cafe.data.AddItem
import com.ssc.cafe.databinding.ItemCafeOrderBinding

class OrderAdapter(var viewModel : HomeViewModel):
    ListAdapter<AddItem, OrderAdapter.OrderViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder (ItemCafeOrderBinding
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

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val add: AddItem = getItem(position)
        holder.bind(add,viewModel)
    }


    class OrderViewHolder(private var binding: ItemCafeOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(add: AddItem, viewModel : HomeViewModel) {
            binding.order = add
            binding.viewModel = viewModel

//            binding.cartButtonPlus.setOnClickListener {
//                Log.i("Sophie_button","add")
//                var add = binding.cartProductQauntity.text.toString().toInt()
//                binding.cartProductQauntity.text = (add+1).toString()
//                if(add >= addToCart.product_stock) {
//                    binding.cartProductQauntity.text = add.toString()
//                }
//                addToCart.product_quantity = binding.cartProductQauntity.text.toString()
//                viewModel.onUpdate(addToCart)
//
//            }
//            binding.cartButtonSub.setOnClickListener {
//                Log.i("Sophie_button","sub")
//                var sub = binding.cartProductQauntity.text.toString().toInt()
//                binding.cartProductQauntity.text = (sub-1).toString()
//                if(sub <= 0) {
//                    binding.cartProductQauntity.text = "0"
//                }
//                addToCart.product_quantity = binding.cartProductQauntity.text.toString()
//                viewModel.onUpdate(addToCart)
//            }
//            binding.cartProductDelete.setOnClickListener {
//                viewModel.deleteItemById(addToCart.productId.toLong())
//
//            }
            binding.executePendingBindings()
        }

    }

}