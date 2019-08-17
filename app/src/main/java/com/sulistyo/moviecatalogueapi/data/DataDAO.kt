package com.sulistyo.moviecatalogueapi.data

import androidx.room.*
import com.sulistyo.moviecatalogueapi.data.model.MovieFavorite
import com.sulistyo.moviecatalogueapi.data.model.TvFavorite
import io.reactivex.Flowable

@Dao
interface DataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTv(tvFavorite: TvFavorite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movieFavorite: MovieFavorite)

    @Query("select * from MOVIE_FAV order by id asc")
    fun getMovieFav(): Flowable<List<MovieFavorite>>

    @Query("select * from TV_FAV order by id asc")
    fun getTvFav(): Flowable<List<TvFavorite>>

    @Query("delete from TV_FAV where id=:id")
    fun deleveTv(id: Int)

    @Query("delete from MOVIE_FAV where id=:id")
    fun deleteMovie(id: Int)


}