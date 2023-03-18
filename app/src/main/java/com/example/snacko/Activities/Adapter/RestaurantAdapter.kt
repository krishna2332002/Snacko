package com.example.snacko.Activities.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snacko.Activities.Activity.LoginActivity
import com.example.snacko.Activities.Activity.RestaurantFoodListActivity
import com.example.snacko.Activities.Model.Restaurant
import com.example.snacko.R
import kotlinx.android.synthetic.main.restaurant_view_holder.view.*

class RestaurantAdapter (
    private var context: Context,
    private var restaurantList: ArrayList<Restaurant>,
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.restaurant_view_holder, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        var restaurant = restaurantList[position]
        holder.itemView.apply {
            restName.setText(restaurant.restName)
//            var drawableResourceId=holder.itemView.context.resources.getIdentifier(restaurant.resPic,"drawable",holder.itemView.context.packageName)
//            Glide.with(this)
//                .load(drawableResourceId)
//                .override(1000, 1000)
//                .placeholder(R.drawable.burger)
//                .into(restPic)
        }
        holder.itemView.setOnClickListener {
            var intent = Intent(context, RestaurantFoodListActivity::class.java)
            intent.putExtra("RestUid",restaurant.restUid)
            intent.putExtra("RestName",restaurant.restName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

}