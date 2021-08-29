package com.suganth.shoppinglistmvvm.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItemEntity (
    @ColumnInfo(name = "item_name")
    var name : String,
    @ColumnInfo(name = "shopping_amount")
    var amount : Int
        ) {
    @PrimaryKey(autoGenerate = true)
    var id:Int ?= null
}