package com.smith.moviespaging.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(MovieConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MoviesDatabase? = null
        fun getDatabase(context: Context, coroutineScope: CoroutineScope): MoviesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "movie_database"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            coroutineScope.launch {
                                INSTANCE?.let {
                                    insertData(context, it.movieDao())
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance

            }
        }

        suspend fun insertData(context: Context, dao: MovieDao) {
            dao.deleteAll()
            context.assets.open("movies.json").bufferedReader().use { inputStream ->
                val movie = object : TypeToken<List<Movie>>() {}.type
                val movieList: List<Movie> = Gson().fromJson(inputStream.readText(), movie)
                Log.d("seed", "$movieList")
                dao.insertAll(movieList)
            }
        }

    }
}