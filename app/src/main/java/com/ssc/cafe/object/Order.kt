package com.ssc.cafe.`object`

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order (

    var id: String ?= null,
    var account: String,
    var content: Map<String, List<OrderDetail>>,
    var itemCount: Long,
    var price: Long,
    var status: Long,
    var time: Long

) :Parcelable
