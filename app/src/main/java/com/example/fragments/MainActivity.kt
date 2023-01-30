package com.example.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.demoFragBtn.setOnClickListener {
            intent = Intent(this, DemoFragments::class.java)
            startActivity(intent)
        }
        binding.signInBtn.setOnClickListener {
            intent = Intent(this, SignInApp::class.java)
            startActivity(intent)
        }
    }
}