package com.rudresh05.pathai.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.FragmentProfileBinding
import com.rudresh05.pathai.ui.AuthActivity

class ProfileFragment: Fragment() {
    private  val binding : FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate((layoutInflater))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        var storageRef = FirebaseStorage.getInstance()

        // set user name and email in profile fragment
        val currentUser = auth.currentUser
       if(currentUser != null){
           binding.profileName.text = currentUser.displayName
           binding.profileEmail.text = currentUser.email
       }
        else{
            binding.profileName.text = "Name Not set yet"
           binding.profileEmail.text = "Email Not set yet"
       }

        if(currentUser!= null) {
            storageRef.getReference("profile_images/${auth.currentUser?.uid}").downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this)
                    .load(uri)
                    .placeholder(R.drawable.doreman)
                    .into(binding.profileImage)
            }
        }

        binding.editProfile.setOnClickListener {
            val editFragment = EditProfileFragment()
            parentFragmentManager.beginTransaction().replace(R.id.container,editFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.signout.setOnClickListener {

            // Sign out the user from Firebase Authentication
            auth.signOut()

            // Navigate to the login or home screen after signing out
            val intent = Intent(requireContext(), AuthActivity::class.java)

            // Clear the back stack and start the login activity
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            // Finish the current activity
            requireActivity().finish()

        }
       }

}