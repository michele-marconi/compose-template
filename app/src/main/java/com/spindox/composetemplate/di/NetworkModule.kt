package com.spindox.composetemplate.di

import com.spindox.composetemplate.BuildConfig
import com.spindox.composetemplate.api.ApiRepository
import com.spindox.composetemplate.api.PunkAPIService
import com.spindox.composetemplate.data.RoomTypeConverters
import com.spindox.composetemplate.utility.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(RoomTypeConverters()).addLast(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .also { okHttpClient ->
                /**
                 * Only add [HttpLoggingInterceptor] on debug build
                 */
                if (BuildConfig.DEBUG) {
                    okHttpClient.addInterceptor(getLoggingInterceptor())
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit) =
        ApiRepository(retrofit.create(PunkAPIService::class.java))

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // Register Moshi as a JSON converter for serialization and deserialization of objects.
        .build()
}
