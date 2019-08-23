package com.ssc.cafe.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AddItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (item : AddItem)

    @Update
    fun update (item : AddItem)

    @Query("SELECT * from add_to_cart_table WHERE productId ")
    fun get(): AddItem?

    @Query("DELETE FROM add_to_cart_table WHERE productId = :itemId")
    fun deleteItem(itemId: Long)

    @Query("DELETE FROM add_to_cart_table")
    fun clear()

    @Query("SELECT * FROM add_to_cart_table ORDER BY productId DESC")
    fun getAllProducts(): LiveData<List<AddItem>>

    @Query("SELECT * FROM add_to_cart_table ORDER BY productId DESC LIMIT 1")
    fun getProduct(): AddItem?

}