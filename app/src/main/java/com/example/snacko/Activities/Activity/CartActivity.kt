package com.example.snacko.Activities.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.CartListAdapter
import com.example.snacko.Activities.Model.Food
import com.example.snacko.R
import com.example.snacko.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    lateinit var cartFoodList: ArrayList<Food>
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var cartListAdapter:CartListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartFoodList= arrayListOf()
        linearLayoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.cartView.layoutManager=linearLayoutManager
        cartListAdapter=CartListAdapter(this,cartFoodList)
        // Fetch Cart List Here
        binding.cartView.adapter=cartListAdapter

    }
}