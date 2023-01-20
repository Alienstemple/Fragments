package com.example.fragments.signfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.R
import com.example.fragments.databinding.FragmentOpenProcessBinding

class ChooseImageFragment : Fragment() {
    private var _binding: FragmentOpenProcessBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOpenProcessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO binding. button_choose_image button_next clickListener,
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChooseImageFragment()
    }
}