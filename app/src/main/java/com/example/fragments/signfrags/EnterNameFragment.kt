package com.example.fragments.signfrags

import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import com.example.fragments.R
import com.example.fragments.data.Account
import com.example.fragments.databinding.FragmentEnterNameBinding
import com.example.fragments.databinding.FragmentFirstBinding
import com.example.fragments.databinding.FragmentOpenProcessBinding
import com.example.fragments.navigator

class EnterNameFragment : Fragment() {
    private var _binding: FragmentEnterNameBinding? = null
    private val binding get() = _binding!!

    private lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        account = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Account.DEFAULT
//        account = savedInstanceState?.getParcelable(KEY_OPTIONS)
//            ?: arguments?.getParcelable(KEY_OPTIONS)
//                    ?: throw IllegalArgumentException("Fragments needs to be open with arguments!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentEnterNameBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.enterNameBtn.setOnClickListener {

            if(binding.enterNameTv.text.isEmpty()) {
                val dialogBuilder = AlertDialog.Builder(requireContext())

                dialogBuilder.setMessage("Вы ввели пустое имя!")

                .setCancelable(false)

                .setPositiveButton("ОК", DialogInterface.OnClickListener {
                    dialog, id -> Toast.makeText(context, "Empty. OK", Toast.LENGTH_SHORT).show()
                    })

                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Предупреждение")
                alert.show()
            } else {

                Log.d(TAG, "Btn pressed.")
                account = account.copy(name = binding.enterNameTv.text.toString())
                Log.d(TAG, "In account : ${account.name}")
//            navigator().publishResult(account)
                Log.d(TAG, "Result published")

                // test set name
                navigator().setName(binding.enterNameTv.text.toString())

                // Go to choose image fragment
                navigator().showChooseImage()
            }
        }
//       requireArguments().getInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear memory
    }

    companion object {
        const val TAG = "EnterNameFragLog"
        private const val KEY_OPTIONS = "key_options"
        fun createArgs(account: Account) = bundleOf(KEY_OPTIONS to account)

        @JvmStatic
        fun newInstance() =
            EnterNameFragment().apply {
                arguments = bundleOf(KEY_OPTIONS to account)
            }
    }
}