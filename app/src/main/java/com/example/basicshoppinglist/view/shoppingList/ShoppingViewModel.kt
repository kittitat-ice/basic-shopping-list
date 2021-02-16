package com.example.basicshoppinglist.view.shoppingList

import androidx.lifecycle.ViewModel
import com.example.basicshoppinglist.data.entities.ShoppingItem
import com.example.basicshoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {
    fun upsert(item: ShoppingItem) =
        CoroutineScope(Dispatchers.IO).launch { repository.upsert(item) }

    fun delete(item: ShoppingItem) =
        CoroutineScope(Dispatchers.IO).launch { repository.delete(item) }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}