package com.gram15inch.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.clone.mycoupang.domain.policy.AllErrorState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketException
import java.net.UnknownHostException

abstract class ErrorHandleViewModel : ViewModel() {

    private val _state = MutableStateFlow(AllErrorState.NONE)
    val errorState = _state.asStateFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        viewModelScope.launch {
            when (throwable) {
                is SocketException -> {
                    Timber.tag("error").d("exceptionHandler.SocketException: ${throwable.message}")
                    _state.emit(AllErrorState.SOCKET)
                }
                is HttpException -> {
                    Timber.tag("error").d("exceptionHandler.HttpException: ${throwable.message}")
                    _state.emit(AllErrorState.HTTP)

                }
                is UnknownHostException -> {
                    Timber.tag("error").d("exceptionHandler.UnknownHostException: ${throwable.message}")
                    _state.emit(AllErrorState.UNKNOWN_HOST)
                }
                else -> {
                    Timber.tag("error").d("exceptionHandler.elseException: ${throwable.message}")
                    _state.emit(AllErrorState.FAIL)
                }
            }
        }
    }

    fun setState(state: AllErrorState) {
        viewModelScope.launch(exceptionHandler) {
            _state.emit(state)
        }
    }
}
