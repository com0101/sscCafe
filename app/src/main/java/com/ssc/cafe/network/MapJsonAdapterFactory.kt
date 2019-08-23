package com.waynechen.w74latte.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.ssc.cafe.`object`.Order

/**
 * Created by Wayne Chen on 2019-08-22.
 */
class MapJsonAdapterFactory {

    @ToJson
    fun toJson(map: HashMap<String, Any>): Map<String, Any> {
        return map
    }

    @FromJson
    fun fromJson(json: Map<String, Any>): HashMap<String, Any> {
        val result = hashMapOf<String, Any>()
        for ((key, value) in json) {
            result[key] = value
        }
        return result
    }
}

fun Map<String, Order>.toOrderList(): List<Order>? {
    val list = mutableListOf<Order>()
    for ((key, value) in this) {
        list.add(value.apply { id = key })
    }
    return list
}
