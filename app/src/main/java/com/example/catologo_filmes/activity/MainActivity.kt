package com.example.catologo_filmes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.example.catologo_filmes.R
import com.example.catologo_filmes.databinding.ActivityMainBinding
import com.example.catologo_filmes.fragment.ViewMoreFragmentDirections


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonBack.visibility = View.VISIBLE
        setUpNavigationListener()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun setUpNavigationListener() {
        val fragmentManager =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
        val navController = fragmentManager?.findNavController()

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            binding.navHostFragmentContainer.apply {
                when (destination.id) {
                    (R.id.homeFragment) -> {
                        binding.textViewToolbar.text = getText(R.string.app_name)
                        binding.buttonBack.visibility = View.GONE
                    }
                    (R.id.detailsFragment) -> {
                        binding.textViewToolbar.text = getText(R.string.details_title)
                        binding.buttonBack.visibility = View.VISIBLE
                    }
                    (R.id.viewMoreFragment) -> {
                        binding.textViewToolbar.text = getText(R.string.view_more_title)
                        binding.buttonBack.visibility = View.VISIBLE
                        binding.buttonBack.setOnClickListener {
                            val direction =
                                ViewMoreFragmentDirections.actionViewMoreFragmentToHomeFragment()
                            findNavController().navigate(direction)
                        }
                    }
                }
            }
        }
    }

}