package com.example.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class BlankNameAlertDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener { _, which ->
            parentFragmentManager.setFragmentResult(KEY_REQUEST, bundleOf(KEY_RESPONSE to which))

        }
        return AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle("Предупреждение")
            .setMessage("Вы ввели пустое имя!")
            .setPositiveButton("ОК", listener)
            .setNegativeButton("Cancel", listener)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        Log.d("TAG", "Dialog dismissed")
        super.onDismiss(dialog)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Toast.makeText(context, "Dialog cancelled", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic val TAG = "${BlankNameAlertDialog::class.java}"
        @JvmStatic val KEY_REQUEST = "REQUEST"
        @JvmStatic val KEY_RESPONSE = "RESPONSE"
    }
}