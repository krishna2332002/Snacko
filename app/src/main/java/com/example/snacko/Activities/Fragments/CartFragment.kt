package com.example.snacko.Activities.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.CartListAdapter
import com.example.snacko.Activities.Model.Food
import com.example.snacko.databinding.FragmentCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private lateinit var binding: FragmentCartBinding
    lateinit var cartFoodList: ArrayList<Food>
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var cartListAdapter: CartListAdapter
    private lateinit var fauth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var totalPrice:Double=0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartBinding.inflate(inflater, container, false)
        fauth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance().reference
        cartFoodList= arrayListOf()
        linearLayoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.cartView.layoutManager=linearLayoutManager
        cartListAdapter=CartListAdapter(requireContext(),cartFoodList)

        binding.cartView.adapter=cartListAdapter
        database.child("Profile").child(fauth.currentUser!!.uid).child("cart").addValueEventListener(object :
            ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
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
        return binding.root
    }
}