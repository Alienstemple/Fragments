package com.example.fragments.signfrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.R
import com.example.fragments.data.Account
import com.example.fragments.databinding.FragmentChooseImageBinding
import com.example.fragments.databinding.FragmentOpenProcessBinding
import com.example.fragments.navigator

class ChooseImageFragment : Fragment() {
    private var _binding: FragmentChooseImageBinding? = null
    private val binding get() = _binding!!

    private lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        account = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Account.DEFAULT
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentChooseImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectImageBtn.setOnClickListener {
            Log.d(TAG, "Btn img pressed.")
            account = account.copy(img = 1)
            Log.d(TAG, "In account : ${account.img}")
            navigator().publishResult(account)
            Log.d(TAG, "Result published")
        }
        binding.nextBtn.setOnClickListener {
            navigator() // TODO go to change account data
        }
    }

    companion object {
        private const val TAG = "ChooseImgFragmLog"
        private const val KEY_OPTIONS = "key_options"
        @JvmStatic
        fun newInstance() =
            ChooseImageFragment()
    }
}