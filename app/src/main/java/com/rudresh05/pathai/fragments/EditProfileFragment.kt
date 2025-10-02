package com.rudresh05.pathai.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {


    val binding: FragmentEditProfileBinding by lazy {
        FragmentEditProfileBinding.inflate(layoutInflater)
    }
    var selectedImageUri: Uri? = null
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null) {
            user.photoUrl?.let { uri ->
                Glide.with(this)
                    .load(uri)
                    .placeholder(R.drawable.doreman)
                    .into(binding.editProfileImg)
            }
        }


        binding.editProfileName.setOnClickListener {
            val firstName = binding.editProfileTextFirstName.text.toString().trim()
            val lastName = binding.editProfileTextLastName.text.toString().trim()
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val profileUpdates = userProfileChangeRequest {
                    displayName = "$firstName $lastName"
                }
                auth.currentUser?.updateProfile(profileUpdates)
                    ?.addOnSuccessListener {
                        Toast.makeText(
                            requireContext(),
                            "Profile Name Save successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    ?.addOnFailureListener { e ->
                        Toast.makeText(
                            requireContext(),
                            "failed to save ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(requireContext(), "Please Enter Your Name", Toast.LENGTH_SHORT)
                    .show()
            }

        }


        binding.editProfileImgCardView.setOnClickListener {
            chooseImage()
        }

        binding.editProfileUploadButton.setOnClickListener {
           uploadImage()
        }
        }
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val PICK_IMAGE_REQUEST = 101
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null){
            selectedImageUri = data.data
            binding.editProfileImg.setImageURI(selectedImageUri)
        }
    }

}

private fun EditProfileFragment.uploadImage() {

    if(selectedImageUri != null){
//        binding.mainContainer.visibility = View.GONE
//        binding.loadingContainer.visibility = View.VISIBLE
        val storageRef = FirebaseStorage.getInstance().getReference("profile_images/${auth.currentUser?.uid}")

        storageRef.putFile(selectedImageUri!!)
            .addOnSuccessListener {
                binding.editProfileImg.setImageURI(selectedImageUri)
                Toast.makeText(requireContext(), "Upload SuccessFully", Toast.LENGTH_SHORT).show()
//                binding.mainContainer.visibility = View.VISIBLE
//                binding.loadingContainer.visibility = View.GONE
            }
            .addOnFailureListener {e ->
                Toast.makeText(requireContext(), "Upload Failed ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}
//
private fun EditProfileFragment.chooseImage() {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    val PICK_IMAGE_REQUEST = 101
    startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_REQUEST)

}
