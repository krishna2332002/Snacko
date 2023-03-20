package com.example.snacko.Activities.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snacko.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    private lateinit var fauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fauth=FirebaseAuth.getInstance()
        binding.startBtn.setOnClickListener {
            if(fauth.currentUser==null)
            {
                var intent= Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                var intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}