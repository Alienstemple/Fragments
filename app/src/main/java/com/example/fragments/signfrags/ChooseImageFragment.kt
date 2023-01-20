package com.example.fragments.signfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.R
import com.example.fragments.databinding.FragmentChooseImageBinding
import com.example.fragments.databinding.FragmentOpenProcessBinding
import com.example.fragments.navigator

class ChooseImageFragment : Fragment() {
    private var _binding: FragmentChooseImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            // TODO set image programmatically (ResultFragment.setImage)
        }
        binding.nextBtn.setOnClickListener {
            navigator() // TODO go to change account data
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChooseImageFragment()
    }
}