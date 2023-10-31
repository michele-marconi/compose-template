package com.spindox.composetemplate.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

abstract class BaseRepo {
    // This is a generic function to handle all API calls
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful)
                    Resource.Success(data = response.body())
                else
                    Resource.Error(key = response.errorBody()?.toString() ?: "Something went wrong")
            } catch (e: HttpException) {
                Resource.Error<T>(key = e.message ?: "Something went wrong")
                    .also { Timber.e(e.message) }
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error<T>(key = e.message ?: "Something went wrong")
                    .also { Timber.e(e.message) }
            }
        }
    }
}