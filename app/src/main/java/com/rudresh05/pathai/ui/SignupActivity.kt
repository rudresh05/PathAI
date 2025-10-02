package com.rudresh05.pathai.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.ActivitySignupBinding
import com.shashank.sony.fancytoastlib.FancyToast

class SignupActivity : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        binding.signup.setOnClickListener {
            val userName = binding.nameSignup.text.toString()
            val email = binding.emailSignup.text.toString()
            val password = binding.passwordSignup.text.toString()
            val confPassword = binding.confirmPassword.text.toString()


            if(userName.isEmpty() || email.isEmpty() || password.isEmpty() || confPassword.isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else if(password != confPassword){
                Toast.makeText(this, "password not maching", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    task ->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        val profileUpdates = userProfileChangeRequest {
                            displayName = userName
                        }

                        user?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                            if(profileTask.isSuccessful){
                                Toast.makeText(this, "Sign up Successful", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                            else{
                                Toast.makeText(this, "Sign up Failed : ${profileTask.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                        Toast.makeText(this, "Sign up Failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}