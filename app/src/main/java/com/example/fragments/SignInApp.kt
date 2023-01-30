package com.example.fragments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragments.databinding.ActivitySignInAppBinding
import com.example.fragments.signfrags.*

class SignInApp : AppCompatActivity(), Navigator {
    lateinit var binding: ActivitySignInAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showOpenProcess()
    }

    /**
     * Показываем экран с кнопкой swowOpenProcess
     */
    override fun showOpenProcess() {
        launchDialogFragment(OpenProcessFragment.newInstance())
    }

    override fun showEnterNameGeneral() {
        launchDialogFragment(EnterNameFragment.newInstance())
        launchResultFragment(ResultFragment.newInstance())
    }

    override fun showEnterName() {
        launchDialogFragment(EnterNameFragment.newInstance())
    }

    override fun showChooseImage() {
        launchDialogFragment(ChooseImageFragment.newInstance())
    }

    override fun showChange() {
        launchDialogFragment(ChangeFragment.newInstance())
    }

    override fun goBack() {
        onBackPressed()
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
        private const val TAG = "SignInAppLog"
    }
}