package com.vivcom.videoshop.presentation.shopping_cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vivcom.videoshop.repository.movie.MovieRepository
import com.vivcom.videoshop.repository.persistence.database.entity.Movie

class ShoppingCartViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: MovieRepository =
        MovieRepository(application)
    private var mAllMovies: LiveData<List<Movie>> = mRepository.getMovies()

    fun getAllMovies(): LiveData<List<Movie>> = mAllMovies

    fun setMovie(movie: Movie) {
        mRepository.insertMovie(movie)
    }
}