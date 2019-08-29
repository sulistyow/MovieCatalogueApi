package com.sulistyo.moviecatalogueapi.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tv")
data class TvFavorite(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "posterPath")
    var posterPath: String?
) : Parcelable