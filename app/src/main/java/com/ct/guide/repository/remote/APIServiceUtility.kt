package com.ct.guide.repository.remote

import android.util.Log
import com.ct.app.BuildConfig
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIServiceUtility<T>() where T : IAPIService {
    companion object {
        fun <T : IAPIService> newInstance(): APIServiceUtility<T> {
            return APIServiceUtility()
        }
    }

    internal fun create(service: Class<T>): T {
        val serviceBaseUrl = (service as IAPIService).baseUrl
        val httpUrl = serviceBaseUrl.toHttpUrlOrNull()
        val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("API", message)
            }
        })

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            logger.apply { logger.level = HttpLoggingInterceptor.Level.BODY }
            client.addInterceptor(logger)
        }
        return Retrofit.Builder()
            .baseUrl(httpUrl!!)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(service)
    }
}