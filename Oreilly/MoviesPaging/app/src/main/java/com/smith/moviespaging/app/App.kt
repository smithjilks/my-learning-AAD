package com.smith.moviespaging.app

import android.app.Application
import android.util.Log
import com.smith.moviespaging.data.Repository
import com.smith.moviespaging.data.db.MoviesDatabase
import kotlinx.coroutines.CoroutineScope

class App : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts

    private val db by lazy {
        MoviesDatabase.getDatabase(this, AppScope)
    }

    val repository by lazy {
        Repository(db.movieDao())
    }
}