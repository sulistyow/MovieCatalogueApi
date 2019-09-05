package com.sulistyo.moviecatalogueapi.data

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sulistyo.moviecatalogueapi.data.model.TvFavorite
import io.reactivex.Flowable

@Dao
interface DataDAO {
    //movie
    @Query("select * from movie")
    fun getMovieFav(): Flowable<List<MovieFavorite>>

    @Insert(onConflict = REPLACE)
    fun insertMovie(movieFavorite: MovieFavorite)

    @Query("select * from movie where title =:title")
    fun checkMovie(title: String): List<MovieFavorite>

    @Query("delete from movie where title= :title")
    fun deleteMovie(title: String)

    //tv
    @Query("select * from tv")
    fun getTvFav(): Flowable<List<TvFavorite>>

    @Insert(onConflict = REPLACE)
    fun insertTv(movieFavorite: TvFavorite)

    @Query("select * from tv where name =:name")
    fun checkTv(name: String): List<TvFavorite>

    @Query("delete from tv where name= :name")
    fun deleteTv(name: String)

    @Query("select * from movie order by id asc")
    fun favorite(): Cursor
}