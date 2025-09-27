package com.rudresh05.pathai.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.Fragment
import com.emreesen.sntoast.SnToast
import com.emreesen.sntoast.Type
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.FragmentProfileBinding
import com.shashank.sony.fancytoastlib.FancyToast


class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Dummy values (baad me Firebase se aayenge)
        binding.userName.text = "Rudresh Patel"
        binding.userEmail.text = "rudreshpatel504@gmail.com"
        binding.coursesCompleted.text = "Courses: 5"
        binding.quizzesTaken.text = "Quizzes: 12"

        // Button actions
        binding.btnEditProfile.setOnClickListener {
            FancyToast.makeText(requireContext(),"Hello World !",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show()
        }

        binding.btnSettings.setOnClickListener {
            SnToast.Builder()
                .context(requireContext())
                .type(Type.SUCCESS)
                .message("Success !") //.cancelable(false or true) Optional Default: False
                // .iconSize(int size) Optional Default: 34dp
                // .textSize(int size) Optional Default 18sp
                // .animation(false or true) Optional Default: True
                // .duration(int ms) Optional Default: 3000ms
                // .backgroundColor(R.color.example) Default: It is filled according to the toast type. If an assignment is made, the assigned value is used
                // .icon(R.drawable.example) Default: It is filled according to the toast type. If an assignment is made, the assigned value is used
                .build()
        }

        binding.btnLogout.setOnClickListener {
           // Toast.makeText(requireContext(), "Logout clicked", Toast.LENGTH_SHORT).show()
            var builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Log out")
            builder.setMessage(R.string.alter_des)
            builder.setIcon(R.drawable.logout)
            builder.setPositiveButton("YES"){dialog, which ->
                Toast.makeText(requireContext(), "Logged out SuccessFully", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("NO"){dialog, which ->
                Toast.makeText(requireContext(), "No Logout", Toast.LENGTH_SHORT).show()
            }
            builder.setNeutralButton("CANCEL"){dialog, which ->
                Toast.makeText(requireContext(), " Canceled SuccessFully", Toast.LENGTH_SHORT).show()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            // Future: FirebaseAuth.getInstance().signOut()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

