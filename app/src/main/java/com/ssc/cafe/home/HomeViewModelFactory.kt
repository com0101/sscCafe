package com.ssc.cafe.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssc.cafe.data.AddItemDao

class HomeViewModelFactory(
    private val product: AddItemDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(product, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}