package com.vivcom.videoshop.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.vivcom.videoshop.repository.persistence.database.DatabaseConfig
import com.vivcom.videoshop.repository.persistence.database.dao.MovieDao
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieRepositoryBD(application: Application) {
    private var mMovieDao: MovieDao = DatabaseConfig.getInstance(application).movieDao()
    private var mAllMovies: LiveData<List<Movie>> = mMovieDao.getAllMovies()

    fun getAllMovies(): LiveData<List<Movie>> {
        return mAllMovies
    }

    fun insertMovie(movie: Movie) = GlobalScope.launch {
        mMovieDao.insert(movie)
    }

    fun insertListMovie(movies: List<Movie>) = GlobalScope.launch {
        mMovieDao.insert(movies)
    }
}