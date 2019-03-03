package com.vivcom.videoshop.repository.shoppingcart

import android.app.Application
import androidx.lifecycle.LiveData
import com.vivcom.videoshop.repository.persistence.database.DatabaseConfig
import com.vivcom.videoshop.repository.persistence.database.dao.ShoppingCartDao
import com.vivcom.videoshop.repository.persistence.database.entity.ShoppingCart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingCartRepository(private val application: Application) {

    private var mShoppingCartDao: ShoppingCartDao = DatabaseConfig.getInstance(application).shoppingCartDao()
    private var mAllShoppingCart: LiveData<List<ShoppingCart>> = mShoppingCartDao.getAllShoppingCart()

    fun getAllShoppingCart(): LiveData<List<ShoppingCart>> {
        return mAllShoppingCart
    }

    fun insertMovie(shoppingCart: ShoppingCart) = GlobalScope.launch {
        mShoppingCartDao.insert(shoppingCart)
    }

    fun insertListMovie(movies: List<ShoppingCart>) = GlobalScope.launch {
        mShoppingCartDao.insert(movies)
    }
}