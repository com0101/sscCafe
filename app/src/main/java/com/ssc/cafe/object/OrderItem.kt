package com.ssc.cafe.`object`

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderItem (

    var account: String?=null,
    var content: Map<String,List<OrderDetail>>,
    var itemCount: Long,
    var price: Long,
    var status: Long,
    var time: Long

):Parcelable

@Parcelize
data class OrderDetail (
    var cups: String ?= null,
    var iced: Boolean,
    var sugar: Boolean
):Parcelable
