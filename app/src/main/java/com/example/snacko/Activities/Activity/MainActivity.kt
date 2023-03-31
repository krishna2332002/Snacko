package com.example.snacko.Activities.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snacko.Activities.Adapter.CategoryAdapter
import com.example.snacko.Activities.Adapter.RecommendedAdapter
import com.example.snacko.Activities.Adapter.RestaurantAdapter
import com.example.snacko.Activities.Fragments.CartFragment
import com.example.snacko.Activities.Fragments.HomeFragment
import com.example.snacko.Activities.Fragments.ProfileFragment
import com.example.snacko.Activities.Model.Category
import com.example.snacko.Activities.Model.Food
import com.example.snacko.Activities.Model.Restaurant
import com.example.snacko.Activities.Model.User
import com.example.snacko.R
import com.example.snacko.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firestore.v1.FirestoreGrpc.FirestoreImplBase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var fauth: FirebaseAuth
    private var currentFragment: Int = 1
    private lateinit var homeFragment: HomeFragment
    private lateinit var cartFragment: CartFragment
    private lateinit var profileFragment: ProfileFragment
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance().getReference()
        fauth = FirebaseAuth.getInstance()
        homeFragment = HomeFragment()
        cartFragment = CartFragment()
        profileFragment = ProfileFragment()
        setCurrentFragment(homeFragment)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    setCurrentFragment(homeFragment)
                    currentFragment = 1
                }
                R.id.cart -> {
                    setCurrentFragment(cartFragment)
                    currentFragment = 3
                }
                R.id.profile -> {
                    setCurrentFragment(profileFragment)
                    currentFragment = 4
                }
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.firstFragment, fragment)
            commit()
        }
}