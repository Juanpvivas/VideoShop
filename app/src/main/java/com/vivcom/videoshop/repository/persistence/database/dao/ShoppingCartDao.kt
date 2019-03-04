package com.vivcom.videoshop.repository.persistence.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.vivcom.videoshop.repository.persistence.database.entity.ShoppingCart


@Dao
abstract class ShoppingCartDao : BaseDao<ShoppingCart> {
    @Query("DELETE FROM shopping_cart")
    abstract fun deleteAll()

    @Query("SELECT * from shopping_cart ORDER BY videoId ASC")
    abstract fun getAllShoppingCart(): LiveData<List<ShoppingCart>>

    @Query("SELECT * from shopping_cart WHERE videoId = :id")
    abstract fun getShoppingCartByID(id: String): ShoppingCart

    @Query("DELETE FROM shopping_cart WHERE videoId = :id")
    abstract fun deleteShoppingCart(id: String)
}