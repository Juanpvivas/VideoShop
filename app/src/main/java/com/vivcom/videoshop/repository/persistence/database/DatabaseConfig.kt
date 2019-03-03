package com.vivcom.videoshop.repository.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vivcom.videoshop.repository.persistence.database.dao.MovieDao
import com.vivcom.videoshop.repository.persistence.database.dao.ShoppingCartDao
import com.vivcom.videoshop.repository.persistence.database.entity.Movie
import com.vivcom.videoshop.repository.persistence.database.entity.ShoppingCart


@Database(
    entities = [
        Movie::class,
        ShoppingCart::class
    ], version = 1
)
abstract class DatabaseConfig : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun shoppingCartDao(): ShoppingCartDao

    companion object {

        private const val nameDB = "DataBase"
        @Volatile
        private var INSTANCE: DatabaseConfig? = null

        fun getInstance(context: Context): DatabaseConfig =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseConfig::class.java, nameDB
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
                        ioThread {
                            //getInstance(context).wordDao().insert(PopulateDB().getWords())
                        }
                    }
                })
                .build()
    }
}