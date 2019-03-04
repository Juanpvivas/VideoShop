package com.vivcom.videoshop.repository.persistence.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.vivcom.videoshop.repository.persistence.database.entity.Movie

@Dao
abstract class MovieDao : BaseDao<Movie> {
    @Query("DELETE FROM Movie")
    abstract fun deleteAll()

    @Query("SELECT * from Movie ORDER BY id ASC")
    abstract fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * from Movie WHERE id = :idMovie")
    abstract fun getMovieById(idMovie: String): LiveData<Movie>
}