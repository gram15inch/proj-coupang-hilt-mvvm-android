package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clone.mycoupang.domain.policy.LoginState
import com.clone.mycoupang.domain.policy.verifyEmail
import com.clone.mycoupang.domain.policy.verifyPassword
import com.gram15inch.domain.model.user.LoginRequest
import com.gram15inch.domain.repository.UserRepository
import com.gram15inch.presentation.MyCoupangEatsApplication
import com.gram15inch.presentation.MyCoupangEatsApplication.Companion.X_ACCESS_TOKEN
import com.gram15inch.presentation.base.ErrorHandleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    ErrorHandleViewModel() {
    private val _flowEmail = MutableStateFlow("")
    private val _flowPassword = MutableStateFlow("")
    val loginState = MutableLiveData(LoginState.NONE)


    private fun testUserInput() {
        setEmail("rising123456@rising.co.kr")
        setPassword("202duck!@#$")
    }

    fun clear() {
        viewModelScope.launch(exceptionHandler) {
            _flowEmail.emit("")
            _flowPassword.emit("")
        }

    }

    fun login() {
        //testUserInput()
        viewModelScope.launch(exceptionHandler) {
            val request = LoginRequest(
                _flowEmail.value,
                _flowPassword.value
            )
            when {
                !verifyEmail(request.email) -> {
                    loginState.postValue(LoginState.EMAIL_ERROR)
                }
                !verifyPassword(request.password) -> {
                    loginState.postValue(LoginState.PW_ERROR)
                }
                else -> {
                    userRepository.postLogin(request)
                        .apply {
                            if (this != null) {
                                loginState.postValue(LoginState.SUCCESS)
                                MyCoupangEatsApplication.prefs.setString(
                                    X_ACCESS_TOKEN,
                                    this.jwt
                                )
                            } else
                                loginState.postValue(LoginState.FAIL)
                        }
                }
            }

        }
    }

    fun setEmail(em: String) {
        viewModelScope.launch(exceptionHandler) {
            _flowEmail.emit(em)
        }
    }

    fun setPassword(pw: String) {
        viewModelScope.launch(exceptionHandler) {
            _flowPassword.emit(pw)
        }
    }


}