package com.ssc.cafe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AddItem::class], version = 1,  exportSchema = false)
abstract class AddItemDatabase : RoomDatabase() {

    abstract val addItemDao: AddItemDao

    companion object {
        @Volatile
        private var INSTANCE: AddItemDatabase? = null

        fun getInstance(context: Context): AddItemDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AddItemDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
