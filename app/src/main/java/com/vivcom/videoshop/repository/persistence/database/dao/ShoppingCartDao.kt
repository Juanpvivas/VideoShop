package com.vivcom.videoshop.repository.persistence.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.vivcom.videoshop.repository.persistence.database.entity.ShoppingCart


@Dao
abstract class ShoppingCartDao : BaseDao<ShoppingCart> {
    @Query("DELETE FROM shopping_cart")
    abstract fun deleteAll()

    @Query("SELECT * from shopping_cart ORDER BY id ASC")
    abstract fun getAllShoppingCart(): LiveData<List<ShoppingCart>>
}