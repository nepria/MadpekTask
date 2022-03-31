package com.example.madpektask.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.legacy.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madpektask.R
import com.example.madpektask.ViewModel.MainViewModel
import com.example.madpektask.ViewModel.MainViewModelFactory
import com.example.madpektask.adapter_avatar
import com.example.madpektask.api.AvatarService
import com.example.madpektask.api.RetrofitHelper
import com.example.madpektask.databinding.FragmentMainBinding
import com.example.madpektask.model.User
import com.example.madpektask.repository.Repository

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)


        val memberService = RetrofitHelper.getInstance().create(AvatarService::class.java)

        val repository = Repository(memberService)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        val view = View.inflate(requireContext(), R.layout.custom_dialogue,null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.background_light)

        mainViewModel.avatar.observe(
            viewLifecycleOwner,
            Observer {
                recyclerView.adapter = adapter_avatar(it.data)
                dialog.dismiss()
            })

            return binding.root
    }
}