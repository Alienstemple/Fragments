package com.example.fragments.signfrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.FragmentChangeBinding
import com.example.fragments.navigator


class ChangeFragment : Fragment() {

    private var _binding: FragmentChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentChangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changeImgBtn.setOnClickListener {
            navigator().showChooseImage()  // TODO back to change image
        }

        binding.changeNameBtn.setOnClickListener {
            navigator().showEnterName()
        }

        binding.finishBtn.setOnClickListener {
            navigator().goToMenu()  // TODO check
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChangeFragment()
    }
}