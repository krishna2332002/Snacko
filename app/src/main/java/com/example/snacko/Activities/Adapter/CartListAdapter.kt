package com.example.snacko.Activities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snacko.Activities.Model.Food
import com.example.snacko.R
import kotlinx.android.synthetic.main.cart_view_holder.view.*
import kotlinx.android.synthetic.main.category_view_holder.view.*

class CartListAdapter (
    private var context: Context,
    private var cartList: ArrayList<Food>,
) : RecyclerView.Adapter<CartListAdapter.CartViewHolder>() {
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.cart_view_holder, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var cartItem = cartList[position]
        holder.itemView.apply {
            itemName.setText(cartItem.title)
            oneItemPrice.setText(cartItem.fee.toString())

            var drawableResourceId=holder.itemView.context.resources.getIdentifier(cartItem.pic,"drawable",holder.itemView.context.packageName)
            Glide.with(this)
                .load(drawableResourceId)
                .override(1000, 1000)
                .placeholder(R.drawable.burger)
                .into(categoryPic)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

}