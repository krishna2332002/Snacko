package com.example.snacko.Activities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snacko.Activities.Model.Category
import com.example.snacko.R
import kotlinx.android.synthetic.main.category_view_holder.view.*


class CategoryAdapter (
    private var context: Context,
    private var categoryList: ArrayList<Category>,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.category_view_holder, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var category = categoryList[position]
        holder.itemView.apply {
            categoryName.setText(category.title)
            var drawableResourceId=holder.itemView.context.resources.getIdentifier(category.pic,"drawable",holder.itemView.context.packageName)
            Glide.with(this)
                .load(drawableResourceId)
                .override(1000, 1000)
                .placeholder(R.drawable.burger)
                .into(categoryPic)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}