package com.example.snacko.Activities.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snacko.R
import com.example.snacko.databinding.ActivityRestaurantBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RestaurantActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRestaurantBinding
    private lateinit var database:DatabaseReference
    private lateinit var fauth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}