package com.vivcom.videoshop.presentation.shopping_cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vivcom.videoshop.repository.movie.MovieRepository
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import com.vivcom.videoshop.repository.persistence.database.entity.ShoppingCart
import com.vivcom.videoshop.repository.shoppingcart.ShoppingCartRepository

class ShoppingCartViewModel(application: Application) : AndroidViewModel(application) {
    private var mShoppingCartRepository: ShoppingCartRepository =
        ShoppingCartRepository(application)
    private var mMovieRepository: MovieRepository =
        MovieRepository(application)
    private var mAllShopping: LiveData<List<ShoppingCart>> = mShoppingCartRepository.getAllShoppingCart()
    private lateinit var mMovie: LiveData<Movie>

    fun getAllShopping(): LiveData<List<ShoppingCart>> = mAllShopping

    fun getAllShoppingMovie() {
        val list = mAllShopping.value
        //LiveData<List<Movie>>
    }

    fun deleteAllShopping() {
        mShoppingCartRepository.deleteAll()
    }
}