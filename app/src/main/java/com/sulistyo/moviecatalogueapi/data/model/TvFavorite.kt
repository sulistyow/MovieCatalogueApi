package com.sulistyo.moviecatalogueapi.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "TV_FAV")
data class TvFavorite(
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,
    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String?,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "originCountry")
    var originalName: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "posterPath")
    var posterPath: String?
) : Parcelable