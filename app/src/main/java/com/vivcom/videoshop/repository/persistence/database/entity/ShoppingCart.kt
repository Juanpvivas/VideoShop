package com.vivcom.videoshop.repository.persistence.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
    (
    tableName = "shopping_cart",
    foreignKeys = [
        ForeignKey(
            entity = Movie::class,
            parentColumns = ["id"],
            childColumns = ["videoId"]
        )]
)
data class ShoppingCart(
    @PrimaryKey
    val videoId: String
)