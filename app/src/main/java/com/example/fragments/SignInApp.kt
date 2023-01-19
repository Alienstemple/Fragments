package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragments.databinding.ActivityMainBinding
import com.example.fragments.databinding.ActivitySignInAppBinding

class SignInApp : AppCompatActivity() {
    lateinit var binding: ActivitySignInAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInAppBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            val openProcessFragment = OpenProcessFragment.newInstance()
            val transaction = supportFragmentManager.beginTransaction()
                .add(R.id.dialog_frag_container, openProcessFragment, "OPEN_PROCESS")
            transaction.addToBackStack("OPEN_PROCESS")
            transaction.commit()
        }

    }
}