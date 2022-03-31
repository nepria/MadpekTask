package com.example.madpektask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth

class Login_signup : AppCompatActivity() {
    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)
        val auth = FirebaseAuth.getInstance()
        val time : Long = 2000
        if(auth!!.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

}