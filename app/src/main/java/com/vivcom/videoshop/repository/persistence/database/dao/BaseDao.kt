package com.vivcom.videoshop.repository.persistence.database.dao

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}