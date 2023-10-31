package com.spindox.composetemplate.api

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(val key: String) : Resource<T>(message = key)
}