package com.ssc.cafe.`object`

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class OrderItem (

    var account: String?=null,
    var content: OrderCafe?=null,
    var itemCount: Long,
    var price: Long,
    var status: Long,
    var time: Long

)

data class OrderCafe (
    var Latte: List<OrderDetail>?=null,
    var Mocha: List<OrderDetail>?=null,
    var Americano: List<OrderDetail>?=null,
    var Espresso: List<OrderDetail>?=null,
    var GreenTea: List<OrderDetail>?=null,
    var BlackTea: List<OrderDetail>?=null,
    var SingleOrigin: List<OrderDetail>?=null
)

data class OrderDetail (
    var cups: String ?= null,
    var iced: Boolean,
    var sugar: Boolean
)
