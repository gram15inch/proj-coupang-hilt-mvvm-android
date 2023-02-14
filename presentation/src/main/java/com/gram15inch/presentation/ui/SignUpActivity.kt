package com.gram15inch.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.clone.mycoupang.domain.policy.SignUpState
import com.clone.mycoupang.domain.policy.verifyEmail
import com.clone.mycoupang.domain.policy.verifyPassword
import com.clone.mycoupang.domain.policy.verifySignUpPassword
import com.gram15inch.presentation.viewmodel.SignUpViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ActivitySignUpBinding
import com.gram15inch.mycoupangeats.databinding.LayoutSignUpTermsBinding
import com.gram15inch.presentation.MyCoupangEatsApplication
import com.gram15inch.presentation.base.DataBindingActivity
import com.gram15inch.presentation.ui.dialog.TermsDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignUpActivity : DataBindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    val viewModel: SignUpViewModel by viewModels()
    lateinit var email: LiveData<String>
    lateinit var password: LiveData<String>
    lateinit var userName: LiveData<String>
    lateinit var phone: LiveData<String>
    lateinit var terms: LiveData<Int>
    private lateinit var allTerms: ArrayList<LayoutSignUpTermsBinding>
    private lateinit var adTerms: ArrayList<LayoutSignUpTermsBinding>
    private lateinit var detailTerms: ArrayList<LayoutSignUpTermsBinding>

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        email = viewModel.email.asLiveData()
        password = viewModel.password.asLiveData()
        userName = viewModel.userName.asLiveData()
        phone = viewModel.phone.asLiveData()
        terms = viewModel.terms.asLiveData()

        binding.signUpAgreeBtn.setOnClickListener {
            viewModel.signUp()
        }

        setBar()
        setState()
        setEditText()
        setCbLayouts()
    }

    private fun setBar() {
        setSupportActionBar(binding.signUpToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_exit_x)
            title = getString(R.string.sign_up_str)
        }
    }

    private fun setState() {
        viewModel.signUpState.observe(this) {
            when (it) {
                SignUpState.SUCCESS -> {
                   // showToast("회원가입 성공 jwt: ${MyApplication.prefs.getString(X_ACCESS_TOKEN,"")}")
                    finish()

                }
                SignUpState.FAIL -> {
                    showSnackBar("회원가입 오류발생")
                }
                else -> {}
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun setEditText() {

        //텍스트 유효성 검사
        binding.apply {

            email.observe(this@SignUpActivity) {
                if (it != "")
                    verifyEmail(it)
                        .apply {
                            signUpIdEtLayout.error =
                                if (this) null else getString(R.string.sign_up_email_error_str)
                        }

            }

            password.observe(this@SignUpActivity) {
                if (it != "")
                    verifySignUpPassword(it)
                        .apply {
                            signUpPwEtLayout.error =
                                if (this) null else getString(R.string.sign_up_pw_error_str)
                        }
            }

            userName.observe(this@SignUpActivity) {
                if (it != "")
                    verifyPassword(it)
                        .apply {
                            signUpNameEtLayout.error =
                                if (this) null else getString(R.string.sign_up_name_error_str)
                        }
            }

            phone.observe(this@SignUpActivity) {
                if (it != "")
                    verifyPassword(it)
                        .apply {
                            signUpPnEtLayout.error =
                                if (this) null else getString(R.string.sign_up_pn_error_str)
                        }
            }
        }
    }

    private fun setCbLayouts() = binding.apply {
        createCbLayouts()

        signUpAllCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel!!.terms.emit(1)
                }
                allTerms.forEach {
                    it.signUpCb.isChecked = true
                }

            } else CoroutineScope(Dispatchers.IO).launch { viewModel!!.terms.emit(0) }
        }

        detailTerms.forEach { layout ->
            layout.signUpDetailBtn.setOnClickListener { _ ->
                TermsDialogFragment(layout.terms!!).show(
                    supportFragmentManager,
                    "FloatDialog"
                )
            }
        }
        allTerms.forEach {
            when (it) {
                termsAdTitleLayout -> {
                    it.signUpCb.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked)
                            adTerms.forEach {
                                it.signUpCb.isChecked = true
                            }
                        else
                            signUpAllCb.isChecked = false
                    }
                }
                else -> {
                    it.signUpCb.setOnCheckedChangeListener { _, isChecked ->
                        if (!isChecked)
                            signUpAllCb.isChecked = false
                    }
                }
            }

        }

        adTerms.forEach {
            it.signUpCb.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked)
                    termsAdTitleLayout.signUpCb.isChecked = false
            }
        }

    }

    private fun createCbLayouts() = binding.apply {

        allTerms = arrayListOf(
            termsAgeLayout,
            termsCpLayout,
            termsEfLayout,
            termsPrLayout,
            termsPrtLayout,
            termsMtLayout,
            termsAdTitleLayout,
        )
        adTerms = arrayListOf(
            termsEmLayout,
            termsSmsLayout,
            termsWebLayout,
        )
        detailTerms = arrayListOf(
            termsCpLayout,
            termsEfLayout,
            termsPrLayout,
            termsPrtLayout,
            termsMtLayout,
            termsAdTitleLayout
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 메뉴 버튼
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun testInputRequest() {
        /* email.postValue("ori0001@rising.co.kr")
         password.postValue("202duck!@#$")
         phone.postValue("01012340001")
         userName.postValue("Thiamine")
         terms.postValue(1)*/
    }
}