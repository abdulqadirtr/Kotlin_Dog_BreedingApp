package com.example.kotlindogbreed.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT *FROM Product")
    fun getAll(): List<Product>

    @Query("SELECT *FROM Product where id IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): LiveData<List<Product>>

    @Query("SELECT EXISTS (SELECT 1 FROM product WHERE id=:id)")
    fun isFavorite(id: Int): Int

    @Insert
    fun insertAll(vararg products: Product)

    @Insert
    fun addProduct(product: Product?)

    @Delete
    fun delete(product: Product?)
}