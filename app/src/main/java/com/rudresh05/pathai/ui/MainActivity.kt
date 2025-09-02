package com.rudresh05.pathai.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rudresh05.pathai.R
import com.rudresh05.pathai.adapters.VideoAdapter
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
    private lateinit var videoAdapter: VideoAdapter // Adapter ko bahar define karo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

binding.bottomNavigationView.setOnItemSelectedListener {item ->
    when(item.itemId){
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

}

    private fun replaceFragment(frag: Fragment) {
        val support = supportFragmentManager
        val trans = support.beginTransaction()
        trans.replace(R.id.container, frag)
        trans.commit()
    }
}