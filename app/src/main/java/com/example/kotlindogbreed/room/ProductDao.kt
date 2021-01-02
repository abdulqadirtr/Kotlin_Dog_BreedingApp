package com.example.kotlindogbreed.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT *FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT *FROM Product where breadName IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): LiveData<List<Product>>

    @Query("SELECT EXISTS (SELECT 1 FROM product WHERE breadName=:id)")
    fun isFavorite(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product?)

    @Delete
    fun delete(product: Product?)
}