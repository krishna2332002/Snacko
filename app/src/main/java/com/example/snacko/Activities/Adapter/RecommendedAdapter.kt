package com.example.snacko.Activities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snacko.Activities.Model.Category
import com.example.snacko.Activities.Model.Food
import com.example.snacko.R
import kotlinx.android.synthetic.main.category_view_holder.view.*
import kotlinx.android.synthetic.main.recommended_view_holder.view.*


class RecommendedAdapter (
    private var context: Context,
    private var recommendList: ArrayList<Food>,
) : RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>() {
    class RecommendedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.recommended_view_holder, parent, false)
        return RecommendedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        var recommend = recommendList[position]
        holder.itemView.apply {
            title.setText(recommend.title)
            price.setText(recommend.fee.toString())
            var drawableResourceId=holder.itemView.context.resources.getIdentifier(recommend.pic,"drawable",holder.itemView.context.packageName)
            Glide.with(this)
                .load(drawableResourceId)
                .override(1000, 1000)
                .placeholder(R.drawable.burger)
                .into(pic)
        }
    }

    override fun getItemCount(): Int {
        return recommendList.size
    }

}