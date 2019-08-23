package com.ssc.cafe.order

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssc.cafe.`object`.CafeItem
import com.ssc.cafe.`object`.Order
import com.ssc.cafe.`object`.OrderItem
import com.ssc.cafe.data.AddItem
import com.ssc.cafe.data.AddItemDao
import com.ssc.cafe.network.CafeApi
import kotlinx.coroutines.*

class OrderHistoryViewModel(val data: AddItemDao, app: Application) : AndroidViewModel(app) {

    private val _status = MutableLiveData<List<Order>>()
    val status: LiveData<List<Order>>
        get() = _status


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//    val listCheckout: List<Order>
//        get() {
//            val list = mutableListOf<Order>()
//            products.value?.let {
//                for (product in it) {
//                    val productCheckout = ProductList(
//                        product.productId,
//                        product.product_name,
//                        product.product_price,
//                        Color(product.product_color_name,product.product_color),
//                        product.product_size,
//                        product.product_quantity)
//                    list.add(productCheckout)
//                }
//            }
//            return list
//        }

    init {
        getorder()

    }

    private fun getorder() {
        coroutineScope.launch {
            var getPropertiesDeferred = CafeApi.retrofitService.getorder()
            try {
                val listResult = getPropertiesDeferred.await()
//                _status.value = listResult
                Log.i("Sophie_order", "order: $listResult")

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
//                _status.value = ArrayList()
            }
        }

    }


//    private fun initializeProduct() {
//        coroutineScope.launch {
//            addProduct.value = getProductFromDatabase()
//        }
//    }

    private suspend fun getProductFromDatabase(): AddItem? {
        return withContext(Dispatchers.IO) {
            var product = data?.getProduct()

            product
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}