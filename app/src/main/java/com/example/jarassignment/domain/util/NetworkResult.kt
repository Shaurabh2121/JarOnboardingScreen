package com.example.jarassignment.domain.util

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Throwable) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()

    fun <R> map(transform: (T) -> R): NetworkResult<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> Error(exception)
            is Loading -> Loading
        }
    }

    companion object {
        fun <T> success(data: T): NetworkResult<T> = Success(data)
        fun error(exception: Throwable): NetworkResult<Nothing> = Error(exception)
        fun <T> loading(): NetworkResult<T> = Loading
    }
}