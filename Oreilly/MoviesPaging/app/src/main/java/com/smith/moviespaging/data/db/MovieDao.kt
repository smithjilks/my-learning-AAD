package com.smith.moviespaging.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): PagingSource<Int, Movie>
    //PagingSource returns flow by default

    @Query("SELECT * FROM movies_table")
    fun getMyMovies(): List<Movie>

    @Query(value = "SELECT * FROM movies_table")
    fun getMovies(): Flow<List<Movie>>

    @Query("DELETE FROM movies_table")
    suspend fun deleteAll()


}