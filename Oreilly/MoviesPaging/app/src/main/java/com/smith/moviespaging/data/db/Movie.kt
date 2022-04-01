package com.smith.moviespaging.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "usGross")
    val usGross: Long?,
    @ColumnInfo(name = "worldwideGross")
    val worldwideGross: Long?,
    @ColumnInfo(name = "usDVDSales")
    val usDVDSales: Long?,
    @ColumnInfo(name = "productionBudget")
    val productionBudget: Long?,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String?,
    @ColumnInfo(name = "mPAARating")
    val mPAARating: String?,
    @ColumnInfo(name = "runningTimeMin")
    val runningTimeMin: Long?,
    @ColumnInfo(name = "distributor")
    val distributor: String?,
    @ColumnInfo(name = "source")
    val source: String?,
    @ColumnInfo(name = "majorGenre")
    val majorGenre: String?,
    @ColumnInfo(name = "creativeType")
    val creativeType: String?,
    @ColumnInfo(name = "director")
    val director: String?,
    @ColumnInfo(name = "rottenTomatoesRating")
    val rottenTomatoesRating: Long?,
    @ColumnInfo(name = "imdbRating")
    val imdbRating: Double?,
    @ColumnInfo(name = "imdbVotes")
    val imdbVotes: Long?

)

class MovieConverter {
    @TypeConverter
    fun toMovieList(value: String): List<Movie> {
        val listType = object: TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toMovieListJson(list: List<Movie>): String {
        val type = object : TypeToken<List<Movie>>() {}.type
        return Gson().toJson(list, type)
    }
}
