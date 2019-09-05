package com.sulistyo.moviecatalogueapi.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = MovieFavorite.TABLE)
data class MovieFavorite(
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "overview") var overview: String?,
    @ColumnInfo(name = "posterPath") var posterPath: String?,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
) : Parcelable {
    companion object {
        const val TABLE = "movie"
    }
}

