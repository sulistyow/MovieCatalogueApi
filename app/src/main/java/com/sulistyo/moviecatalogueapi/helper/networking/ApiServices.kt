package com.sulistyo.moviecatalogueapi.helper.networking

import com.sulistyo.moviecatalogueapi.model.movie.kt.ResponseMovie
import com.sulistyo.moviecatalogueapi.model.tv.kt.ResponseTv
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getMovie(@Query("api_key") key: String): Observable<Response<ResponseMovie>>

    @GET("tv/popular")
    fun getTv(@Query("api_key") key: String): Observable<Response<ResponseTv>>

    @GET("search/movie?api_key=${ApiCall.key}")
    fun searchMovie(@Query("query") q: String): Observable<Response<ResponseMovie>>

    @GET("search/tv?api_key=${ApiCall.key}")
    fun searchTv(@Query("query") q: String): Observable<Response<ResponseTv>>

    @GET("discover/movie?sort_by=popularity.desc&api_key=${ApiCall.key}")
    fun cekRelease(@Query("primary_release_date.gte")gte:String,
                   @Query("primary_release_date.lte")lte:String): Call<ResponseMovie>

}
