package com.example.basicshoppinglist.view.shoppingList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicshoppinglist.R
import com.example.basicshoppinglist.data.entities.ShoppingItem
import com.example.basicshoppinglist.view.shoppingList.ShoppingViewModel

class ShoppingItemAdapter(
    var shoppingList: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val item = shoppingList[position]

        holder.itemView.findViewById<TextView>(R.id.tvName).text = item.name
        holder.itemView.findViewById<TextView>(R.id.tvAmount).text = item.amount.toString()

        // delete from database
        holder.itemView.findViewById<Button>(R.id.btRemove).setOnClickListener{
            viewModel.delete(item)
        }

        // add 1 and remove 1
        holder.itemView.findViewById<ImageView>(R.id.ivIncre).setOnClickListener{
            item.amount = item.amount + 1
            viewModel.upsert(item)
        }
        holder.itemView.findViewById<ImageView>(R.id.ivDecre).setOnClickListener{
            if (item.amount == 0) return@setOnClickListener
            item.amount = item.amount - 1
            viewModel.upsert(item)
        }
    }

    override fun getItemCount(): Int = shoppingList.size
}