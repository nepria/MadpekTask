package com.example.madpektask.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madpektask.Login_signup
import com.example.madpektask.MainActivity
import com.example.madpektask.R
import com.example.madpektask.databinding.FragmentLoginBinding
import com.example.madpektask.databinding.FragmentProfileBinding
import com.example.madpektask.model.UserDetails
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentProfileBinding.inflate(inflater, container, false)

        binding.emailid.setText(UserDetails.email)
        binding.name.setText(UserDetails.username)
        binding.username.setText(UserDetails.username)
        auth = FirebaseAuth.getInstance()
        binding.logOut.setOnClickListener {
            auth.signOut()
            val intent = Intent (this@ProfileFragment.context, Login_signup::class.java)
            startActivity(intent)


        }
        return binding.root
    }
}