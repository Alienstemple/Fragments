package com.example.fragments.signfrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import com.example.fragments.R
import com.example.fragments.data.Account
import com.example.fragments.databinding.FragmentChooseImageBinding
import com.example.fragments.databinding.FragmentOpenProcessBinding
import com.example.fragments.navigator
import com.google.android.material.bottomsheet.BottomSheetDialog

class ChooseImageFragment : Fragment() {
    private var _binding: FragmentChooseImageBinding? = null
    private val binding get() = _binding!!

    private lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        account = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Account.DEFAULT
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentChooseImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = this.layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.selectImageBtn.setOnClickListener {
            Log.d(TAG, "Btn img pressed.")

            showDialogNotificationAction(bottomSheetDialog)
            val radiogroup: RadioGroup = bottomSheetView.findViewById(R.id.img_choose_radiogroup)

            radiogroup.setOnCheckedChangeListener { group, checkedId ->
                val imgRes = when(checkedId) {
                    R.id.img_radio_1 -> R.drawable.img1//R.drawable.ic_lock_idle_alarm
                    R.id.img_radio_2 -> R.drawable.img2//R.drawable.ic_menu_copy_material
                    else -> {throw RuntimeException("Except in radiogroup")}
                }

                navigator().setImage(imgRes)
                bottomSheetDialog.dismiss()
            }

        }

        binding.nextBtn.setOnClickListener {
            navigator() // TODO go to change account data
        }
    }

    private fun showDialogNotificationAction(bottomSheetDialog: BottomSheetDialog) {
        bottomSheetDialog.show()

        val bottomSheetDialogFrameLayout =
            bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheetDialogFrameLayout?.background = null
    }

    companion object {
        private const val TAG = "ChooseImgFragmLog"
        private const val KEY_OPTIONS = "key_options"
        @JvmStatic
        fun newInstance() =
            ChooseImageFragment()
    }
}