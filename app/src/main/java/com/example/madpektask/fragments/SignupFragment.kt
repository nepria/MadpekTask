package com.example.madpektask.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.madpektask.R
import com.example.madpektask.databinding.FragmentSignupBinding
import com.example.madpektask.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        binding.loginTextView.setOnClickListener {
            it.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.alreadyAnAccounButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }


        binding.signupBtn.setOnClickListener {

            val view = View.inflate(requireContext(), R.layout.custom_dialogue, null)
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.background_light)
            val username = binding.etUsernameSignup.text.toString().trim()
            val email = binding.etEmailSignup.text.toString().trim()
            val password = binding.etPassSignup.text.toString().trim()
            when {
                TextUtils.isEmpty(username) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(email) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(password) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        database = FirebaseDatabase.getInstance("https://madpek-task-default-rtdb.asia-southeast1.firebasedatabase.app ").getReference("Users")
                        val user = auth.currentUser
                        val userID = user?.uid
                        val User = User(userID, username, email, password)
                        database.child(userID.toString()).setValue(User).addOnSuccessListener {
                            binding.etUsernameSignup.text?.clear()
                            binding.etEmailSignup.text?.clear()
                            binding.etPassSignup.text?.clear()
                            binding.etPassSignup.text?.clear()


                            Toast.makeText(
                                requireContext(),
                                "Account Created successfully",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            auth.signOut()
                            dialog.dismiss()
                            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)


                        }.addOnFailureListener {
                            Toast.makeText(
                                requireContext(),
                                "Enter the details again",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialog.dismiss()
                        }

                    }

                }

            }
        }



        return binding.root
    }
}