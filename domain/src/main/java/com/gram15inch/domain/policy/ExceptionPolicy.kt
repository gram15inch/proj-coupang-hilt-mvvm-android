package com.gram15inch.domain.policy


class ResponseException(message: String) : Exception(message)
enum class ResponseState{SUCCESS,ERROR,NONE}

