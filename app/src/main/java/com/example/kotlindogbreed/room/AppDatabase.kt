package com.example.kotlindogbreed.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Product::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private const val dbName = "productDb"
        private var appDatabase: AppDatabase? = null

        /*
    when the resource belongs to all instances (i.e. when it is in a static variable)
     then you use a synchronized static method to access it.
     */
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (appDatabase == null) {

                    appDatabase = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, dbName).allowMainThreadQueries().build()

            }
            return appDatabase
        }
    }
}