package com.example.madpektask

import android.app.Activity
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.madpektask.databinding.ActivityMainBinding
import com.example.madpektask.fragments.MainFragment
import com.example.madpektask.fragments.ProfileFragment
import com.example.madpektask.model.UserDetails
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navview: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        firebaseReference = FirebaseDatabase.getInstance("https://madpek-task-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users")
        storelocaldata()





        drawerLayout = binding.drawerid
        navview = binding.nav

        toggle = ActionBarDrawerToggle(this,drawerLayout, 0, 0)


        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.frame_layout, MainFragment()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navview.setNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item1 -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_layout, ProfileFragment())
                    fragmentTransaction.commit()
                    drawerLayout.closeDrawers()
                    setTitle("My profile")
                }

                R.id.item2 -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_layout, MainFragment())
                    fragmentTransaction.commit()
                    drawerLayout.closeDrawers()
                    setTitle("Avatars")
                }
            }

            true

        }
    }
    private fun storelocaldata() {
        val currentuser = auth.currentUser
        val currentUserUid = currentuser?.uid
        firebaseReference.get().addOnSuccessListener { snapshot ->

            val children = snapshot.children
            if (currentUserUid != null) {
                children.forEach {
                    if (currentUserUid.equals(it.child("id").value.toString())) {
                        UserDetails.id = it.child("id").value.toString()
                        UserDetails.username = it.child("username").value.toString()
                        UserDetails.email = it.child("email").value.toString()
                        UserDetails.password = it.child("password").value.toString()
                    }

                }

            }


        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}