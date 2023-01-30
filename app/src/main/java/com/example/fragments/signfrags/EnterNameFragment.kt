package com.example.fragments.signfrags

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragments.databinding.FragmentEnterNameBinding
import com.example.fragments.dialogs.BlankNameAlertDialog
import com.example.fragments.navigator

class EnterNameFragment : Fragment() {
    private var _binding: FragmentEnterNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnterNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Устанавливаем слушатель диалогу до входа в onClickListener
        setupBlankNameDialogListener()
        binding.enterNameBtn.setOnClickListener {

            if (binding.enterNameTv.text.isEmpty()) {
                val dialog = BlankNameAlertDialog()
                dialog.show(parentFragmentManager, BlankNameAlertDialog.TAG)
            } else {
                Log.d(TAG, "Btn pressed.")
                // test set name
                navigator().setName(binding.enterNameTv.text.toString())
                // Go to choose image fragment
                navigator().showChooseImage()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear memory
    }

    private fun setupBlankNameDialogListener() {
        // Устанавливаем слушателя
        parentFragmentManager.setFragmentResultListener(BlankNameAlertDialog.KEY_REQUEST,
            this) { _, result ->
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

        @JvmStatic
        fun newInstance() =
            EnterNameFragment()
    }
}