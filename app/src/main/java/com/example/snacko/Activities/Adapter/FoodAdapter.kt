package com.example.snacko.Activities.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snacko.Activities.Activity.DetailActivity
import com.example.snacko.Activities.Model.Food
import com.example.snacko.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.recommended_view_holder.view.*

class FoodAdapter (
    private var context: Context,
    private var foodList: ArrayList<Food>,
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.recommended_view_holder, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        var food = foodList[position]
        holder.itemView.apply {
            title.setText(food.title)
            price.setText(food.fee.toString())
//            var drawableResourceId=holder.itemView.context.resources.getIdentifier(food.pic,"drawable",holder.itemView.context.packageName)
//            Glide.with(this)
//                .load(drawableResourceId)
//                .override(1000, 1000)
//                .placeholder(R.drawable.burger)
//                .into(pic)
            addToCartBtn.setOnClickListener{
                if(FirebaseAuth.getInstance().currentUser==null)
                {
                    Toast.makeText(context, "Login First", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    var database=FirebaseDatabase.getInstance().reference.child("Profile").child(FirebaseAuth.getInstance().currentUser!!.uid).child("cart")
                    database.child(food.id!!).addListenerForSingleValueEvent(object :ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists())
                            {
                                Toast.makeText(context, "Already Added in Cart", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                database.child(food.id!!).setValue(food).addOnSuccessListener {
                                    Toast.makeText(context, "Food Added In cart", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {

                        }
                    })
                }
            }
            holder.itemView.setOnClickListener{
                var intent= Intent(context,DetailActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

}