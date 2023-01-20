package com.example.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fragments.databinding.ActivitySignInAppBinding
import com.example.fragments.signfrags.OpenProcessFragment

class SignInApp : AppCompatActivity(), Navigator {
    lateinit var binding: ActivitySignInAppBinding

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.dialog_frag_container) as NavHostFragment)
            .navController
    }

    private var currentFragment: Fragment? = null

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)

            if (f !is NavHostFragment) {
                currentFragment = f
//                updateUi()  // TODO update
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

    override fun goBack() {
        onBackPressed()  // TODO check
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        supportFragmentManager.setFragmentResult(
            result.javaClass.name,
            bundleOf(KEY_RESULT to result)
        )
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>,
    ) {
        supportFragmentManager.setFragmentResultListener(clazz.name, owner) { _, bundle ->
            listener.invoke(bundle.getParcelable(KEY_RESULT)!!)
        }
    }

    companion object {
        @JvmStatic
        private val KEY_RESULT = "RESULT"
    }
}