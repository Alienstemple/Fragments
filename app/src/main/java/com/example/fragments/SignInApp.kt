package com.example.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import com.example.fragments.databinding.ActivitySignInAppBinding
import com.example.fragments.signfrags.OpenProcessFragment

class SignInApp : AppCompatActivity(), Navigator {
    lateinit var binding: ActivitySignInAppBinding

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)

            if (f !is NavHostFragment) {
//                currentFragment = f  // TODO
//                updateUi()
            }
        }
    }

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

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        //TODO("Not yet implemented")
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>,
    ) {
        //TODO("Not yet implemented")
    }
}