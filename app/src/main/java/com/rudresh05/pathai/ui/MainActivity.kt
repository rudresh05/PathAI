package com.rudresh05.pathai.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.ActivityMainBinding
import com.rudresh05.pathai.fragments.CoursesFragment
import com.rudresh05.pathai.fragments.HomeFragment
import com.rudresh05.pathai.fragments.ProfileFragment
import com.rudresh05.pathai.fragments.QuizFragment
import com.rudresh05.pathai.fragments.SearchFragment
import com.rudresh05.pathai.models.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeId -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.searchId -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.courseId -> {
                    replaceFragment(CoursesFragment())
                    true
                }
                R.id.quizId -> {
                    replaceFragment(QuizFragment())
                    true
                }
                R.id.profileId -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        val auth = FirebaseAuth.getInstance()
        val storageRef = FirebaseStorage.getInstance()
        val headerView = binding.navigationView.getHeaderView(0)
        val image = headerView.findViewById<ImageView>(R.id.navUserImg)
        val name = headerView.findViewById<TextView>(R.id.navUserName)
        val email = headerView.findViewById<TextView>(R.id.navUserEmail)
        val currentUser = auth.currentUser
        if(currentUser!= null) {
            storageRef.getReference("profile_images/${auth.currentUser?.uid}").downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this)
                    .load(uri)
                    .placeholder(R.drawable.doreman)
                    .into(image)
            }
            name.text = currentUser.displayName
            email.text = currentUser.email
        }
        // Drawer logic
        binding.drawerToggleBtn.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun replaceFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, frag)
            .commit()
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Search button action
                true
            }
            R.id.action_profile -> {
                // Profile button action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


