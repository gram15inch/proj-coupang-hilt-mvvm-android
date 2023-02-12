package com.gram15inch.presentation.base

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.clone.mycoupang.domain.policy.AllErrorState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.StateFlow


abstract class DataBindingActivity<T : ViewDataBinding>(val layout: Int) : AppCompatActivity() {
    protected lateinit var binding: T
    var errorState: StateFlow<AllErrorState>? = null

    init {
        setErrorState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this
    }

    private fun setErrorState() {
        if (errorState != null)
            when (errorState!!.value) {
                AllErrorState.SUCCESS -> {}
                AllErrorState.NONE -> {}
                else -> {
                    showSnackBar("서버오류")
                }
            }
    }
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    fun showDialog(message: String) {
        val dlg: AlertDialog.Builder = AlertDialog.Builder(this)
        dlg.setMessage(message) // 메시지

        dlg.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which -> //토스트 메시지
            dialog.dismiss()
        })
        dlg.show()

    }


}