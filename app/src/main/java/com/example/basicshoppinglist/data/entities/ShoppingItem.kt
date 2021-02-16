package com.example.basicshoppinglist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey val name: String,
    var amount: Int
) {
}