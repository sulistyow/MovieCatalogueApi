package com.sulistyo.moviecatalogueapi.helper.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCall {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    const val key = "ac59030a1c6c2e80e782d6eb5458482a"

    const val img = "http://image.tmdb.org/t/p/w185"

    private fun retrofit(url: String): Retrofit? {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60 * 5, TimeUnit.SECONDS)
            .readTimeout(60 * 5, TimeUnit.SECONDS)
            .writeTimeout(60 * 5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun instance() = retrofit(BASE_URL)!!.create(ApiServices::class.java)!!

}