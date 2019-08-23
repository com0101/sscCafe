package com.ssc.cafe.`object`

data class OrderItem (

    var account: String,
    var content: OrderCafe

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
    var iced: String,
    var sugar: String
)
