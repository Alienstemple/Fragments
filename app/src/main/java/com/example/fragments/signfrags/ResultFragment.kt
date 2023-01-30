package com.example.fragments.signfrags

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    fun setName(name: String) {
        Log.d("TAG", "Set name = $name")
        binding.nameTv.text = name
    }

    fun setImage(img: Int) {
        binding.userImg.setImageResource(img)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear memory
    }

    companion object {
        private const val TAG = "ResultFragmLog"

        @JvmStatic
        fun newInstance() =
            ResultFragment()
    }
}