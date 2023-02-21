package com.example.snacko.Activities.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.CategoryAdapter
import com.example.snacko.Activities.Model.Category
import com.example.snacko.R
import com.example.snacko.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var categoryList: ArrayList<Category>
    lateinit var categoryAdapter:CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryView()
        popularView()
    }

    private fun popularView() {

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