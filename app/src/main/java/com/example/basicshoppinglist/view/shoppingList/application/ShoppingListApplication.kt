package com.example.basicshoppinglist.view.shoppingList.application

import android.app.Application
import com.example.basicshoppinglist.data.db.ShoppingDatabase
import com.example.basicshoppinglist.data.repositories.ShoppingRepository
import com.example.basicshoppinglist.view.shoppingList.ShoppingViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class ShoppingListApplication : Application(), DIAware {
  override val di: DI = DI.lazy {
    import(androidXModule(this@ShoppingListApplication))
    bind() from singleton { ShoppingDatabase(instance()) }
    bind() from singleton { ShoppingRepository(instance()) }
    bind() from provider { ShoppingViewModelFactory(instance()) }
  }
}