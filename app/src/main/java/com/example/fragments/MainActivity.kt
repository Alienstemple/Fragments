package com.example.fragments

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import com.example.fragments.databinding.ActivityMainBinding
import kotlin.random.Random

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