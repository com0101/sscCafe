package com.ssc.cafe.`object`


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CafeItem (

    var image: String,
    var name: String,
    var price: Long

): Parcelable