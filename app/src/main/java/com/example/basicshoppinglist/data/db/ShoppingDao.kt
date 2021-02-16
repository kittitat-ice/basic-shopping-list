package com.example.basicshoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.basicshoppinglist.data.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items ORDER BY name ASC")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}