package com.ssc.cafe.order

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssc.cafe.data.AddItemDao

class OrderViewModelFactory(
    private val product: AddItemDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderHistoryViewModel::class.java)) {
            return OrderHistoryViewModel(product, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}