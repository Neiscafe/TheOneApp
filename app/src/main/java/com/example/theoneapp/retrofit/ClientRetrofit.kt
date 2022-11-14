package com.example.theoneapp.retrofit

import android.content.Context
import com.example.theoneapp.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_URL = "https://the-one-api.dev/v2/"

class ClientRetrofit {
    companion object {
        fun create(context: Context?): Api {
            val token = "y2KfFlWuUFC95lRiaQvn"
//            val logger = HttpLoggingInterceptor()
//
//            logger.level = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor.Level.BODY
//            } else {
//                HttpLoggingInterceptor.Level.NONE
//            }
            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(OAuthInterceptor("Bearer", token))
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(Api::class.java)
        }
    }
}