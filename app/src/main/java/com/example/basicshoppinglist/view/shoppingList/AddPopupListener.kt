package com.example.basicshoppinglist.view.shoppingList

import com.example.basicshoppinglist.data.entities.ShoppingItem

interface AddPopupListener {
    fun onAddButtonClicked(item: ShoppingItem)
}