package com.example.snacko.Activities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snacko.Activities.Model.Food
import com.example.snacko.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
            allItemPrice.setText((cartItem.fee.times(cartItem.numberInCart)).toString())
            currentNoOfItem.setText(cartItem.numberInCart.toString())

            plusCartBtn.setOnClickListener{
                FirebaseDatabase.getInstance().reference.child("Profile").child(FirebaseAuth.getInstance().currentUser!!.uid).child("cart").child(cartItem.id.toString())
                    .child("numberInCart").setValue(cartItem.numberInCart+1)
                Toast.makeText(context, cartItem.numberInCart.toString(), Toast.LENGTH_SHORT).show()
            }
            minusCartBtn.setOnClickListener{
                FirebaseDatabase.getInstance().reference.child("Profile").child(FirebaseAuth.getInstance().currentUser!!.uid).child("cart").child(cartItem.id.toString())
                    .child("numberInCart").setValue(cartItem.numberInCart-1)
                cartItem.numberInCart = cartItem.numberInCart.minus(1)
            }
//            var drawableResourceId=holder.itemView.context.resources.getIdentifier(cartItem.pic,"drawable",holder.itemView.context.packageName)
//            Glide.with(this)
//                .load(drawableResourceId)
//                .override(1000, 1000)
//                .placeholder(R.drawable.burger)
//                .into(itemPic)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

}