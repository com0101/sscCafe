package com.ssc.cafe.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ssc.cafe.`object`.*
import com.ssc.cafe.data.AddItem
import com.ssc.cafe.data.AddItemDao
import com.ssc.cafe.network.CafeApi
import kotlinx.coroutines.*

class HomeViewModel(val data: AddItemDao, app: Application) : AndroidViewModel(app) {

    private val _status = MutableLiveData<List<CafeItem>>()
    val status: LiveData<List<CafeItem>>
        get() = _status

    private val _order = MutableLiveData<OrderName>()
    val order: LiveData<OrderName>
        get() = _order

    var product = MutableLiveData<CafeItem>()



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private var addProduct = MutableLiveData<AddItem?>()

//    val productDatabase = AddItemDatabase.getInstance(ApplicationContext.applicationContext()).addItemDao
    val selectedProduct = data.getAllProducts()
    val countInCart: LiveData<Int> = Transformations.map(selectedProduct) { it.size }
    val price: LiveData<Int> = Transformations.map(selectedProduct) { it -> it.sumBy { it.product_price.toInt() } }
    val contentName: LiveData<List<String>> = Transformations.map(selectedProduct) { it -> it.map { it.product_name   } }
    init {
        getItems()
        initializeProduct()
    }

    private fun getItems() {
        coroutineScope.launch {
            var getPropertiesDeferred = CafeApi.retrofitService.getItems()
            try {
                val listResult = getPropertiesDeferred.await()
                _status.value = listResult
            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
//                _status.value = ArrayList()
            }
        }

    }

    fun postItems(orderItem: OrderItem) {
        coroutineScope.launch {
            var getPropertiesDeferred = CafeApi.retrofitService.postItems(orderItem)
            try {
                val listResult = getPropertiesDeferred.await()
                _order.value = listResult

                Log.i("Sophie_post", "post=${order.value}")

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }

    fun post() {
        postItems(OrderItem(
            "sophie@74latte.com",
            mapOf(product.value!!.name to listOf(OrderDetail("1",true,false))),
            countInCart.value!!.toLong(),
            price.value!!.toLong(),
            0,
            12312331321
        ))
    }



    private fun initializeProduct() {
        coroutineScope.launch {
            addProduct.value = getProductFromDatabase()
        }
    }

    private suspend fun getProductFromDatabase(): AddItem? {
        return withContext(Dispatchers.IO) {
            var product = data?.getProduct()

            product
        }
    }

    private suspend fun insert(product: AddItem) {
        withContext(Dispatchers.IO) {
            data?.insert(product)
        }
    }


    private suspend fun get() : Boolean {
        return withContext(Dispatchers.IO) {
            data.get() != null
        }
    }

    fun addToBottom() {
        coroutineScope.launch {

            val newProduct = AddItem(0,
                product.value!!.image,
                product.value!!.name,
                product.value!!.price)
                insert(newProduct)
            addProduct.value = getProductFromDatabase()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}