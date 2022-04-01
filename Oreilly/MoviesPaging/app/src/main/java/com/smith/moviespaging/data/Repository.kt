package com.smith.moviespaging.data

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.smith.moviespaging.data.db.Movie
import com.smith.moviespaging.data.db.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Declares the DAO as a private property in the constructor. Pass in the DAO
 * instead of the whole database, because you only need access to the DAO
*/
class Repository(private val movieDao: MovieDao) {

    /**
     * Room executes all queries on a separate thread.
     * Observed Flow will notify the observer when the data has changed.
    */
    val allMoviesFlow: Flow<List<Movie>> = movieDao.getMovies()

    /**
     * By default Room runs suspend queries off the main thread, therefore, we don't need to
     * implement anything else to ensure we're not doing long running database work
     * off the main thread.
    */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    val getAllPaging = Pager(PagingConfig(pageSize = 5, enablePlaceholders = true),
        pagingSourceFactory = { movieDao.getAllMovies() }
    ).flow

   // suspend fun getMyMovies(): List<Movie> = withContext(Dispatchers.IO) { movieDao.getMyMovies() }
}