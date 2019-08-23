package com.ssc.cafe.order


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.ssc.cafe.R
import com.ssc.cafe.data.AddItemDatabase
import com.ssc.cafe.databinding.FragmentOrderHistoryBinding
import com.ssc.cafe.home.HomeViewModel
import com.ssc.cafe.home.HomeViewModelFactory

class OrderHistoryFragment : Fragment() {
    private lateinit var binding: FragmentOrderHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderHistoryBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val product = AddItemDatabase.getInstance(application).addItemDao
        val viewModelFactory = OrderViewModelFactory(product , application)
        val viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(OrderHistoryViewModel::class.java)

        binding.viewModel = viewModel


        return binding.root
    }


}
