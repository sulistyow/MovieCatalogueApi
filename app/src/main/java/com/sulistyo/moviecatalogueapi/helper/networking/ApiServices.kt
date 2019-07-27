package com.sulistyo.moviecatalogueapi.helper.networking

import com.sulistyo.moviecatalogueapi.model.movie.ResponseMovie
import com.sulistyo.moviecatalogueapi.model.tv.ResponseTv
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getMovie(@Query("api_key") key: String): Observable<Response<ResponseMovie>>

    @GET("tv/popular")
    fun getTv(@Query("api_key") key: String): Observable<Response<ResponseTv>>


}
