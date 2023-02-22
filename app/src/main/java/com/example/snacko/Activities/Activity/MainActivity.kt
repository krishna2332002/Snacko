package com.example.snacko.Activities.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.CategoryAdapter
import com.example.snacko.Activities.Adapter.RecommendedAdapter
import com.example.snacko.Activities.Model.Category
import com.example.snacko.Activities.Model.Food
import com.example.snacko.R
import com.example.snacko.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var categoryList: ArrayList<Category>
    lateinit var categoryAdapter:CategoryAdapter
    lateinit var linearLayoutManager2: LinearLayoutManager
    lateinit var recommendedList: ArrayList<Food>
    lateinit var recommendedAdapter:RecommendedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryView()
        recommendedView()
    }

    private fun recommendedView() {
        recommendedList= arrayListOf()
        linearLayoutManager2 = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recommendedView.layoutManager = linearLayoutManager2
        recommendedList.add(Food("Pepperoni Pizza","pizza1","Slices Pepperoni, Mozzarella Cheese, Fresh Oregano",100.0,10,20,1000))
        recommendedList.add(Food("Cheese Burger","burger","Beef, Gouda Cheese, Special sauce",50.0,5,10,400))
        recommendedList.add(Food("Vegetable Pizza","pizza3","Olive Oil, Vegetable Oil, Cherry Tomato",80.0,6,24,1000))
        recommendedAdapter=RecommendedAdapter(this,recommendedList)
        binding.recommendedView.adapter = recommendedAdapter
    }

    private fun categoryView() {
        categoryList= arrayListOf()
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.categoryView.layoutManager = linearLayoutManager
        categoryList.add(Category("Pizza","cat_1"))
        categoryList.add(Category("Burger","cat_2"))
        categoryList.add(Category("HotDog","cat_3"))
        categoryList.add(Category("Drink","cat_4"))
        categoryList.add(Category("Donut","cat_5"))
        categoryAdapter=CategoryAdapter(this,categoryList)
        binding.categoryView.adapter = categoryAdapter
    }

}