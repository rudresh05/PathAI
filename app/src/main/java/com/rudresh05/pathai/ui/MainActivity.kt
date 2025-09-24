package com.rudresh05.pathai.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
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
