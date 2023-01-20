package com.example.fragments.signfrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.R
import com.example.fragments.data.Account
import com.example.fragments.databinding.FragmentFirstBinding
import com.example.fragments.databinding.FragmentOpenProcessBinding
import com.example.fragments.databinding.FragmentResultBinding
import com.example.fragments.navigator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {
    private lateinit var account: Account
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        account = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Account.DEFAULT
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated() called")
        navigator().listenResult(Account::class.java, viewLifecycleOwner) {
            Log.d(TAG, "listenResult called")
            account = it
            binding.nameTv.text = account.name
            Log.d(TAG, "account = ${account.name}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear memory
    }

    fun setImage() {
        Log.d("DebugImg", "In setImage()")
        binding.userImg.setImageResource(R.drawable.ic_launcher_foreground)
    }

    companion object {
        private const val TAG = "ResultFragmLog"
        private const val KEY_OPTIONS = "key_options"

        @JvmStatic
        fun newInstance() =
            ResultFragment()
    }
}