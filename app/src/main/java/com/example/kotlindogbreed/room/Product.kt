package com.example.kotlindogbreed.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product {
    @PrimaryKey(autoGenerate = true)
     var id: Int = 0

    @ColumnInfo(name = "breadName")
    var fName: String? = null

    @ColumnInfo(name = "breadLastName")
    var lName: String? = null


}