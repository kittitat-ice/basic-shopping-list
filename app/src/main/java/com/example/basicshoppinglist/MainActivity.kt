package com.example.basicshoppinglist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicshoppinglist.data.db.ShoppingDatabase
import com.example.basicshoppinglist.data.entities.ShoppingItem
import com.example.basicshoppinglist.data.repositories.ShoppingRepository
import com.example.basicshoppinglist.view.shoppingList.AddPopupListener
import com.example.basicshoppinglist.view.shoppingList.AddShoppingItemPopup
import com.example.basicshoppinglist.view.shoppingList.ShoppingViewModel
import com.example.basicshoppinglist.view.shoppingList.ShoppingViewModelFactory
import com.example.basicshoppinglist.view.shoppingList.adapter.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        val rvShoppingList = findViewById<RecyclerView>(R.id.rvShoppingList)
        rvShoppingList.layoutManager = LinearLayoutManager(this)
        rvShoppingList.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.shoppingList = it
            adapter.notifyDataSetChanged()
        })

        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener{
            AddShoppingItemPopup(this, object : AddPopupListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}