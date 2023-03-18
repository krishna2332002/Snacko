package com.example.snacko.Activities.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.FoodAdapter
import com.example.snacko.Activities.Model.Food
import com.example.snacko.R
import com.example.snacko.databinding.ActivityRestaurantFoodListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.protobuf.Value

class RestaurantFoodListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRestaurantFoodListBinding
    private lateinit var fauth:FirebaseAuth
    private lateinit var database:DatabaseReference
    private lateinit var foodList:ArrayList<Food>
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var restUid:String
    private lateinit var restName:String
    private var x: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRestaurantFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fauth= FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance().reference
        restUid= intent.getStringExtra("RestUid").toString()
        restName= intent.getStringExtra("RestName").toString()
        Toast.makeText(this, restUid, Toast.LENGTH_SHORT).show()
        binding.restaurantName.setText(restName)
        foodList= arrayListOf()
        foodAdapter= FoodAdapter(this,foodList)
        binding.restaurantFoodList.layoutManager=GridLayoutManager(this,2)
        binding.restaurantFoodList.adapter=foodAdapter
        database.child("Restaurants").child(restUid).child("restFoodList").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount == x) {
                    Toast.makeText(
                        this@RestaurantFoodListActivity,
                        "Not added in any group",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else
                {
                    Toast.makeText(
                        this@RestaurantFoodListActivity,
                        " group",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                 foodList.clear()
                for(dataSnapshot in snapshot.children)
                {
                    var food=dataSnapshot.getValue(Food::class.java)
                    if(food!=null)
                    {
                        foodList.add(food)
                    }
                }
                foodAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}