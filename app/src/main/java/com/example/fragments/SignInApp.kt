package com.example.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fragments.databinding.ActivitySignInAppBinding
import com.example.fragments.signfrags.*

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
            savedInstanceState: Bundle?,
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)

            if (f !is NavHostFragment) {
                currentFragment = f
//                showOpenProcess() // TODO test
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
        showOpenProcess()
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    /**
     * Показываем экран с кнопкой swowOpenProcess
     */
    override fun showOpenProcess() {
        launchDialogFragment(OpenProcessFragment())
    }

    override fun showEnterNameGeneral() {
        launchDialogFragment(EnterNameFragment())
        launchResultFragment(ResultFragment())
    }

    override fun showEnterName() {
        launchDialogFragment(EnterNameFragment())
    }

    override fun showChooseImage() {
        launchDialogFragment(ChooseImageFragment())
    }

    override fun showChange() {
        launchDialogFragment(ChangeFragment())
    }

    override fun goBack() {
        onBackPressed()  // TODO check
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        launchDialogFragment(OpenProcessFragment())
    }

    override fun setName(name: String) {
        Log.d(TAG, "setName() called")
        val resultFrag = supportFragmentManager.findFragmentByTag("fragment_result")
        Log.d(TAG, "Foung fragment ${resultFrag?.tag}")
        (resultFrag as ResultFragment).setName(name)
    }

    override fun setImage(img: Int) {
        Log.d(TAG, "setImage() called")
        val resultFrag = supportFragmentManager.findFragmentByTag("fragment_result")
        Log.d(TAG, "Foung fragment ${resultFrag?.tag}")
        (resultFrag as ResultFragment).setImage(img)
    }

    override fun <T : Parcelable> publishResult(result: T) {
//        supportFragmentManager.findFragmentById(57)
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

    /**
     * Вызываем replace и добавляем в backstack
     */
    private fun launchDialogFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.dialog_frag_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun launchResultFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.result_frag_container, fragment, "fragment_result")
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        private val KEY_RESULT = "RESULT"
        private const val TAG = "SignInAppLog"
    }
}