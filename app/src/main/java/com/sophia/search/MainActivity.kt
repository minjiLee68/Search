package com.sophia.search

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sophia.search.ViewModel.AppViewModel
import com.sophia.search.ViewModel.AppViewModelFactory
import com.sophia.search.databinding.ActivityMainBinding
import com.sophia.search.databinding.FragInformationBinding
import kotlin.math.min

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding


    private val viewModel by viewModels<AppViewModel> {
        AppViewModelFactory(application)
    }

    private val editFragment = EditFragment()
    private val inforFragment = InforFragment()
    private val minjiFragment = MinjiFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        binding.navBottom.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.information -> {
                        replaceFragment(inforFragment)
                    }
                    R.id.edit -> {
                        replaceFragment(editFragment)
                    }
                    R.id.minji -> {
                        replaceFragment(minjiFragment)
                    }
                }
                true
            }
            selectedItemId = R.id.edit
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragment.id, fragment).commit()
    }

}