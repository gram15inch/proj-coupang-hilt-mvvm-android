package com.gram15inch.presentation.ui.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gram15inch.presentation.ui.LoginActivity
import com.gram15inch.presentation.ui.SignUpActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.FragmentLoginDialogBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginDialogFragment : BottomSheetDialogFragment() {
    var _binding : FragmentLoginDialogBinding? = null
    val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner =this
        binding.loginDialogEmBtn.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            this.dismiss()
        }
        binding.loginDialogSignUpTxt.setOnClickListener {
            startActivity(Intent(requireContext(), SignUpActivity::class.java))
            this.dismiss()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}