package com.ssc.cafe.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.ssc.cafe.`object`.CafeItem
import com.ssc.cafe.`object`.Order
import com.ssc.cafe.`object`.OrderItem
import com.waynechen.w74latte.network.MapJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*



private const val BASE_URL = "https://latte-ccbd9.firebaseio.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(MapJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()




private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()



interface CafeService {

    @GET("items.json")
    fun getItems():
            Deferred<List<CafeItem>>

    @GET("orders.json")
    fun getorder(@Query("orderBy") orderBy : String = "\"account\"",
                 @Query("equalTo") equalTo : String = "\"sophie@74latte.com\""):
            Deferred<List<Order>>

    @POST("orders.json")
    fun postItems(@Body orderItem: OrderItem):
            Deferred<OrderItem>
}


object CafeApi {
    val retrofitService : CafeService by lazy { retrofit.create(CafeService::class.java) }
}
