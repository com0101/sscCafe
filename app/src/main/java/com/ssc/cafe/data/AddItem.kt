package com.ssc.cafe.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_to_cart_table")
data class AddItem (

    @PrimaryKey(autoGenerate = true)
    var productId : Int ,

    @ColumnInfo(name = "product_image")
    var product_image : String,

    @ColumnInfo(name = "product_name")
    var product_name : String,

    @ColumnInfo(name = "product_price")
    var product_price : Long,

    @ColumnInfo(name = "product_type")
    var product_type : String?= null,

    @ColumnInfo(name = "product_sugar")
    var product_sugar : String?= null

)