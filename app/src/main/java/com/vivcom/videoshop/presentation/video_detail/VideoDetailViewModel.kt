package com.vivcom.videoshop.presentation.video_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vivcom.videoshop.repository.movie.MovieRepository
import com.vivcom.videoshop.repository.persistence.database.entity.Movie

class VideoDetailViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: MovieRepository =
        MovieRepository(application)
    private var mAllMovies: LiveData<List<Movie>> = mRepository.getMovies()

    fun getAllMovies(): LiveData<List<Movie>> = mAllMovies

    fun setMovie(movie: Movie) {
        mRepository.insertMovie(movie)
    }
}