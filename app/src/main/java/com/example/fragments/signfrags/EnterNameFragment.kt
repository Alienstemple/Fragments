package com.example.fragments.signfrags

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragments.data.Account
import com.example.fragments.databinding.FragmentEnterNameBinding
import com.example.fragments.dialogs.BlankNameAlertDialog
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

        // Устанавливаем слушатель диалогу до входа в onClickListener
        setupBlankNameDialogListener()
        binding.enterNameBtn.setOnClickListener {

            if(binding.enterNameTv.text.isEmpty()) {
                val dialog = BlankNameAlertDialog()
                dialog.show(parentFragmentManager, BlankNameAlertDialog.TAG)
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

    private fun setupBlankNameDialogListener() {
        // Устанавливаем слушателя
        parentFragmentManager.setFragmentResultListener(BlankNameAlertDialog.KEY_REQUEST, this) { _, result ->
            when (result.getInt(BlankNameAlertDialog.KEY_RESPONSE)) {
                DialogInterface.BUTTON_POSITIVE -> Toast.makeText(context, "OK", Toast.LENGTH_SHORT)
                    .show()
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(context,
                    "Cancel",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val TAG = "EnterNameFragLog"
        private const val KEY_OPTIONS = "key_options"
        fun createArgs(account: Account) = bundleOf(KEY_OPTIONS to account)

        @JvmStatic
        fun newInstance() =
            EnterNameFragment()
    }
}