package com.example.fragments.signfrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.FragmentOpenProcessBinding
import com.example.fragments.navigator

class OpenProcessFragment : Fragment() {
    private var _binding: FragmentOpenProcessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOpenProcessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openProcessBtn.setOnClickListener {
            navigator().showEnterNameGeneral()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear memory
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            OpenProcessFragment()
    }
}