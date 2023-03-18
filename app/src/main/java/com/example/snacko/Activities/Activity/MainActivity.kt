package com.example.snacko.Activities.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.CategoryAdapter
import com.example.snacko.Activities.Adapter.RecommendedAdapter
import com.example.snacko.Activities.Adapter.RestaurantAdapter
import com.example.snacko.Activities.Model.Category
import com.example.snacko.Activities.Model.Food
import com.example.snacko.Activities.Model.Restaurant
import com.example.snacko.R
import com.example.snacko.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firestore.v1.FirestoreGrpc.FirestoreImplBase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var categoryList: ArrayList<Category>
    private lateinit var restaurantList: ArrayList<Restaurant>
    private lateinit var restaurantAdapter:RestaurantAdapter
    private lateinit var categoryAdapter:CategoryAdapter
    private lateinit var linearLayoutManager2: LinearLayoutManager
    private lateinit var recommendedList: ArrayList<Food>
    private lateinit var recommendedAdapter:RecommendedAdapter
    private lateinit var fauth:FirebaseAuth
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fauth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance().reference

        categoryView()
        recommendedView()
        restaurantView()

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
    private fun restaurantView() {
        restaurantList= arrayListOf()
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.restaurantView.layoutManager = linearLayoutManager
        database.child("Restaurants").addValueEventListener(object :ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                restaurantList.clear()
                for (dataSnapshot in snapshot.children) {
                    val rest = dataSnapshot.getValue(Restaurant::class.java)
                    if (rest != null) {
                        restaurantList.add(rest)
                    }
                }
                restaurantAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        restaurantAdapter=RestaurantAdapter(this,restaurantList)
        binding.restaurantView.adapter = restaurantAdapter
    }

}