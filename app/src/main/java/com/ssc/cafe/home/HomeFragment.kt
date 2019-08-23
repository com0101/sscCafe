package com.ssc.cafe.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ssc.cafe.data.AddItemDatabase
import com.ssc.cafe.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit  var binding : FragmentHomeBinding

//    private val viewModel: HomeViewModel by lazy {
//        ViewModelProviders.of(this).get(HomeViewModel::class.java)
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val product = AddItemDatabase.getInstance(application).addItemDao
        val viewModelFactory = HomeViewModelFactory(product , application)
        val viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        binding.itemList.adapter = ItemAdapter(ItemAdapter.OnClickListener{
            Toast.makeText(context, "itempick", Toast.LENGTH_SHORT).show()
            viewModel.product.value = it
            viewModel.selectedProduct.observe(this, Observer {
                Log.i("Sophie_selectedProduct" , "selectedProduct: $it")
            })

        },viewModel)

        binding.orderList.adapter = OrderAdapter(viewModel)

        viewModel.product.observe(this, Observer {
            viewModel.addToBottom()
            Log.i("Sophie_product" , "product: $it")
        })


        return binding.root
    }


}
