package com.vivcom.videoshop.repository.persistence.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_cart")
data class ShoppingCart(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val videoId: String
)