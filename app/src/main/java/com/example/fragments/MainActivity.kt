package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import com.example.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add new fragment
        binding.addBtn.setOnClickListener {
            if (savedInstanceState == null) {
                val firstFragment = FirstFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .add(R.id.test_frag_container, firstFragment)
                    .commit()
            }
        }


    }
}