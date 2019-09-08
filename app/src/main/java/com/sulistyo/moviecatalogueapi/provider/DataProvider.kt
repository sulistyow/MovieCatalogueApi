package com.sulistyo.moviecatalogueapi.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.sulistyo.moviecatalogueapi.data.FavoriteDb
import com.sulistyo.moviecatalogueapi.data.MovieFavorite

class DataProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.sulistyo.moviecatalogueapi.provider"
    }

    private val DIR = 1
    private val ITEM = 2

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, MovieFavorite.TABLE, DIR)
        uriMatcher.addURI(AUTHORITY, MovieFavorite.TABLE + "/*", ITEM)
    }

    private var db: FavoriteDb? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(
        uri: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        if (code == DIR || code == ITEM) {
            val dao = context?.let { FavoriteDb.getInstance(it)?.dataDao() }
            var cursor: Cursor? = null
            if (code == DIR) {
                cursor = dao?.favorite()
            }
            cursor?.setNotificationUri(context?.contentResolver, uri)

            return cursor
        } else {
            throw IllegalArgumentException("Unknown $uri")
        }
    }

    override fun onCreate(): Boolean {
        db = context?.let { FavoriteDb.getInstance(it) }
        return false
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(p0: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}