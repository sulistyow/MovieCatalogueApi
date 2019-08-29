package com.sulistyo.moviecatalogueapi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sulistyo.moviecatalogueapi.data.model.TvFavorite

@Database(entities = [MovieFavorite::class, TvFavorite::class], version = 2)
abstract class FavoriteDb : RoomDatabase() {

    abstract fun dataDao(): DataDAO

    companion object {
        private var INSTANCE: FavoriteDb? = null
        fun getInstance(context: Context): FavoriteDb? {
            if (INSTANCE == null) {
                synchronized(FavoriteDb::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDb::class.java,
                        "favorites.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}