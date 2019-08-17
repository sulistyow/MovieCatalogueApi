package com.sulistyo.moviecatalogueapi.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "MOVIE_FAV")
data class MovieFavorite(
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "posterPath")
    var posterPath: String?,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,
    @ColumnInfo(name = "title")
    var title: String?
) : Parcelable