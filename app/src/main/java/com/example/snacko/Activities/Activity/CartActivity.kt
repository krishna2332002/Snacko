package com.example.snacko.Activities.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.CartListAdapter
import com.example.snacko.Activities.Model.Food
import com.example.snacko.Activities.Model.Restaurant
import com.example.snacko.R
import com.example.snacko.databinding.ActivityCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    lateinit var cartFoodList: ArrayList<Food>
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var cartListAdapter:CartListAdapter
    private lateinit var fauth: FirebaseAuth
    private lateinit var database:DatabaseReference
    private var totalPrice:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fauth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance().reference
        cartFoodList= arrayListOf()
        linearLayoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.layoutManager=linearLayoutManager
        cartListAdapter=CartListAdapter(this,cartFoodList)

        binding.cartView.adapter=cartListAdapter
        database.child("Profile").child(fauth.currentUser!!.uid).child("cart").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cartFoodList.clear()
                totalPrice=0.0
                for (dataSnapshot in snapshot.children) {
                    val food = dataSnapshot.getValue(Food::class.java)
                    if (food != null) {
                        cartFoodList.add(food)
                        totalPrice+=food.numberInCart*food.fee
                    }
                }
                cartListAdapter.notifyDataSetChanged()
                binding.totalFee.setText(totalPrice.toString())
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })

        bottomNavigationView()

    }
    private fun bottomNavigationView() {
        binding.cartBtn.setOnClickListener {
            var intent= Intent(this,CartActivity::class.java)
            startActivity(intent)
        }
        binding.homeBtn.setOnClickListener {
            var intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}