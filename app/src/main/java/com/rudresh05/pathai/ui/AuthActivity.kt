package com.rudresh05.pathai.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private val binding: ActivityAuthBinding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }
private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()

        auth = FirebaseAuth.getInstance()
        var currentUser: FirebaseUser? = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.authLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.authSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }


    }
}