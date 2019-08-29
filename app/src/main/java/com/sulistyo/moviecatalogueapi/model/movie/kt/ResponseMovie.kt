package com.sulistyo.moviecatalogueapi.model.movie.kt

import com.google.gson.annotations.SerializedName

data class ResponseMovie(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<DataMovies>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)