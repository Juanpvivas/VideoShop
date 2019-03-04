package com.vivcom.videoshop.presentation.video

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.vivcom.videoshop.repository.movie.MovieRepository
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import com.vivcom.videoshop.repository.persistence.database.entity.ShoppingCart
import com.vivcom.videoshop.repository.shoppingcart.ShoppingCartRepository

class VideoViewModel(application: Application) : AndroidViewModel(application) {
    private var mMovieRepository: MovieRepository = MovieRepository(application)
    private var mShoppingCartRepository: ShoppingCartRepository = ShoppingCartRepository(application)
    private var mAllMovies: LiveData<List<Movie>> = mMovieRepository.getMovies()
    private var mAllShoppingCart: LiveData<List<ShoppingCart>> = mShoppingCartRepository.getAllShoppingCart()

    fun getAllMovies(): LiveData<List<Movie>> = mAllMovies

    fun setMovie(movie: Movie) {
        mMovieRepository.insertMovie(movie)
    }

    fun addShoppingCart(movie: Movie) {
        val shoppingCart = ShoppingCart(movie.id)
        mShoppingCartRepository.insertShopping(shoppingCart)
    }

    fun deleteShoppingCart(movie: Movie) {
        mShoppingCartRepository.deleteShoppingCart(movie.id)
    }

    fun getMovieById(idMovie: String): LiveData<Movie> {
        return mMovieRepository.getMovieById(idMovie)
    }

    fun getAllShoppingCart(): List<LiveData<Movie>> {
        val listMovie = emptyList<LiveData<Movie>>().toMutableList()
        mAllShoppingCart.observeForever { list ->
            list.forEach {
                listMovie.add(getMovieById(it.videoId))
            }
        }
        return listMovie
    }
}