package com.example.fragments

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.fragments.databinding.ActivityDemoFragmentsBinding
import com.example.fragments.databinding.ActivityMainBinding
import kotlin.random.Random

class DemoFragments : AppCompatActivity() {
    lateinit var binding: ActivityDemoFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoFragmentsBinding.inflate(layoutInflater)
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

        binding.replaceBtn.setOnClickListener {
            if (savedInstanceState == null) {
                val secondFragment = FirstFragment.newInstance()

                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.test_frag_container, SecondFragment.newInstance())
                }

            }
        }

        binding.removeBtn.setOnClickListener {
            if (savedInstanceState == null) {
                supportFragmentManager.commit {
                    supportFragmentManager.fragments.forEach { remove(it) }
                }
            }
        }
    }
}