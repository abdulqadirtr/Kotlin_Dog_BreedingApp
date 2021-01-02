package com.example.kotlindogbreed.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity  (indices = arrayOf(Index(value = ["breadName", "breadLastName"],
    unique = true)))
class Product {
    @PrimaryKey(autoGenerate = true)
     var id: Int = 0

    @ColumnInfo(name = "breadName")
    var fName: String? = null

    @ColumnInfo(name = "breadLastName")
    var lName: String? = null


}