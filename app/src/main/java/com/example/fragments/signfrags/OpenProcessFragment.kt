package com.example.fragments.signfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragments.R
import com.example.fragments.databinding.FragmentFirstBinding
import com.example.fragments.databinding.FragmentOpenProcessBinding
import com.example.fragments.navigator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var _binding: FragmentOpenProcessBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [OpenProcessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpenProcessFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOpenProcessBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val enterNameFragment = EnterNameFragment.newInstance()
        val resultFragment = ResultFragment.newInstance()
        binding.openProcessBtn.setOnClickListener {
            navigator().showEnterName()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear memory
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OpenProcessFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            OpenProcessFragment()
    }
}