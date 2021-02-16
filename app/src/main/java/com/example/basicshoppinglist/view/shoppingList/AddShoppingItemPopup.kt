package com.example.basicshoppinglist.view.shoppingList

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doOnTextChanged
import com.example.basicshoppinglist.R
import com.example.basicshoppinglist.data.entities.ShoppingItem

class AddShoppingItemPopup(context: Context, var addPopupListener: AddPopupListener) :
  AppCompatDialog(context) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.popup_add_shopping_item)

    this.setTitle("Add a new item")
    this.setCancelable(false)

    val etName = findViewById<EditText>(R.id.etName)!!
    val etAmount = findViewById<EditText>(R.id.etAmount)!!
    val btSubmit = findViewById<Button>(R.id.btSubmit)!!
    val btCancel = findViewById<Button>(R.id.btCancel)!!
    val tvSummary = findViewById<TextView>(R.id.tvSummary)!!
    var isNameEmpty = true
    var isAmountEmpty = true

    btSubmit.isEnabled = !(isNameEmpty || isAmountEmpty)

    etName.doOnTextChanged { text, _, _, _ ->
      isNameEmpty = text.isNullOrBlank()

      if (isAmountEmpty || isNameEmpty) {
        btSubmit.isEnabled = false
        tvSummary.text = ""
        return@doOnTextChanged
      }
      tvSummary.text = context.getString(R.string.popup_summary, etAmount.text, etName.text)
      btSubmit.isEnabled = true
    }

    etAmount.doOnTextChanged { text, _, _, _ ->
      isAmountEmpty = text.isNullOrBlank()

      if (isAmountEmpty || isNameEmpty) {
        btSubmit.isEnabled = false
        tvSummary.text = ""
        return@doOnTextChanged
      }
      tvSummary.text = context.getString(R.string.popup_summary, etAmount.text, etName.text)
      btSubmit.isEnabled = true
    }

    btSubmit.setOnClickListener {
      val name = etName.text.toString()
      val amount = etAmount.text.toString().toInt()

      val item = ShoppingItem(name, amount)
      addPopupListener.onAddButtonClicked(item)
      dismiss()
    }

    etAmount.setOnEditorActionListener { _, _, _ ->
      btSubmit.callOnClick()
    }

    btCancel.setOnClickListener {
      cancel()
    }
  }

}