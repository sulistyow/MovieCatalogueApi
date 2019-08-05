package com.sulistyo.moviecatalogueapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
    val overview: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val video: Boolean?,
    val title: String?,
    val genreIds: List<Int?>?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val popularity: Double?,
    val id: Int?,
    val adult: Boolean?,
    val voteCount: Int?
) : Parcelable