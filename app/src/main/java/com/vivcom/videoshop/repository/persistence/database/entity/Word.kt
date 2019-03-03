package com.vivcom.videoshop.repository.persistence.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    val mWord: String
)