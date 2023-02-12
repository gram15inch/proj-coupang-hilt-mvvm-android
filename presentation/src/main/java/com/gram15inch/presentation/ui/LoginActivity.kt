package com.gram15inch.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.LiveData

import com.clone.mycoupang.domain.policy.LoginState
import com.gram15inch.presentation.viewmodel.LoginViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ActivityLoginBinding
import com.gram15inch.presentation.MyCoupangEatsApplication
import com.gram15inch.presentation.MyCoupangEatsApplication.Companion.X_ACCESS_TOKEN
import com.gram15inch.presentation.base.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : DataBindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var loginState: LiveData<LoginState>

    val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginState = viewModel.loginState

        setState()
        setUiBar()
        setUiEditText()
        setUiButton()

    }

    override fun onResume() {
        super.onResume()
        viewModel.clear()
    }

    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    private fun setState() {
        loginState.observe(this) { state ->
            when (state) {
                LoginState.SUCCESS -> {
                    finish()
                   /* showToast(
                        "로그인 성공 jwt: ${
                            MyApplication.prefs.getString(
                                MyApplication.X_ACCESS_TOKEN,
                                ""
                            )
                        }"
                    )*/
                }
                LoginState.FAIL -> {
                    showDialog("입력하신 아이디 또는 비밀번호가 일치하지 않습니다.")
                }
                LoginState.EMAIL_ERROR -> {
                    showSnackBar("아이디를 입력해주세요")
                }
                LoginState.PW_ERROR -> {
                    showSnackBar("비밀번호를 입력해주세요")
                }
                else -> {}
            }
        }
    }

    private fun setUiBar() {
        setSupportActionBar(binding.loginToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(R.drawable.ic_exit_x_tb)
        }
    }

    private fun setUiEditText() {
        binding.loginIdTxt.editTextValue.observe(this) { viewModel.setEmail(it) }
        binding.loginPwTxt.editTextValue.observe(this) { viewModel.setPassword(it) }
    }

    private fun setUiButton() {
        binding.loginEnterBtn.setOnClickListener {
            viewModel.login()
        }

        binding.loginSignUpTxt.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.loginLogoImg.setOnClickListener {
            showToast("${MyCoupangEatsApplication.prefs.getString(X_ACCESS_TOKEN, "없음")}")
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 메뉴 버튼
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}