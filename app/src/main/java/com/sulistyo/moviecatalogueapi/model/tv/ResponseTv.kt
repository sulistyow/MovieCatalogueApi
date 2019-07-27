package com.sulistyo.moviecatalogueapi.model.tv

import com.google.gson.annotations.SerializedName

data class ResponseTv(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<DataTv>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)