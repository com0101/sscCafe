package com.ssc.cafe.order

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssc.cafe.`object`.Order
import com.ssc.cafe.data.AddItem
import com.ssc.cafe.data.AddItemDao
import com.ssc.cafe.network.CafeApi
import com.waynechen.w74latte.network.toOrderList
import kotlinx.coroutines.*

class OrderHistoryViewModel(val data: AddItemDao, app: Application) : AndroidViewModel(app) {

    private val _status = MutableLiveData<List<Order>>()
    val status: LiveData<List<Order>>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    init {
        getOrder()

    }

    private fun getOrder() {
        coroutineScope.launch {
            var getPropertiesDeferred = CafeApi.retrofitService.getOrder()
            try {
                val listResult = getPropertiesDeferred.await()
                _status.value = listResult.toOrderList()?.sortedByDescending { it.time }


                Log.i("Sophie_order", "order: ${_status.value}")

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
//                _status.value = ArrayList()
            }
        }

    }


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