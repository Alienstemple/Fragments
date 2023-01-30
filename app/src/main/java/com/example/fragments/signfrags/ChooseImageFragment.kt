package com.example.fragments.signfrags

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentChooseImageBinding
import com.example.fragments.navigator
import com.google.android.material.bottomsheet.BottomSheetDialog

class ChooseImageFragment : Fragment() {
    private var _binding: FragmentChooseImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
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

            radiogroup.setOnCheckedChangeListener { _, checkedId ->
                val imgRes = when (checkedId) {
                    R.id.img_radio_1 -> R.drawable.img1
                    R.id.img_radio_2 -> R.drawable.img2
                    else -> {
                        throw RuntimeException("Except in radiogroup")
                    }
                }

                navigator().setImage(imgRes)
                bottomSheetDialog.dismiss()
            }

        }

        binding.nextBtn.setOnClickListener {
            Log.d(TAG, "Next pressed")
            navigator().showChange()
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

        @JvmStatic
        fun newInstance() =
            ChooseImageFragment()
    }
}