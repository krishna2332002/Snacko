package com.example.snacko.Activities.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.snacko.R
import com.example.snacko.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var fauth: FirebaseAuth
    private var verificationId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fauth = FirebaseAuth.getInstance()
        binding.sendOtpBtn.setOnClickListener {
            if (binding.userPhoneNumber.text.equals(null)) {
                Toast.makeText(this, "Enter Valid Phone number!!", Toast.LENGTH_SHORT).show()
            } else {
                var phone = "+91" + binding.userPhoneNumber.text.toString()
                sendVerificationCode(phone)
            }
        }
        binding.loginBtn.setOnClickListener {
            if (binding.otp.text.equals(null)) {
                Toast.makeText(this, "Enter Valid Otp!!", Toast.LENGTH_SHORT).show()
            } else {
                verifyCode(binding.otp.text.toString())
            }
        }

    }

    private fun sendVerificationCode(phone: String) {
        var options: PhoneAuthOptions = PhoneAuthOptions.newBuilder(fauth).setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS).setActivity(this).setCallbacks(mCallBack).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(s: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                verificationId = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                    binding.otp.setText(code)
                    verifyCode(code)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        fauth.signInWithCredential(credential)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}