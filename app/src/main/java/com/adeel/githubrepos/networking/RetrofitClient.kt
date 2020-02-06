package com.adeel.githubrepos.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://github-trending-api.now.sh/"

fun getHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val builder = OkHttpClient.Builder()
    builder.addInterceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("Accept", "application/vnd.github.v3+json")
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }
    builder.addInterceptor(loggingInterceptor)

    return builder.build()
}

fun getRetrofitClient(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getHttpClient()).build()
}