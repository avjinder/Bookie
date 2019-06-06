package com.avjinder.sekhon.bookie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avjinder.sekhon.bookie.daos.BookCategoryDao
import com.avjinder.sekhon.bookie.models.BookCategory

@Database(entities = [BookCategory::class], version = 1)
abstract class BookieDatabase : RoomDatabase() {

    abstract fun bookCategoriesDao(): BookCategoryDao

    companion object {
        private var DB_INSTANCE: BookieDatabase? = null
        private const val DB_NAME = "bookie.db"

        fun createDatabase(ctx: Context): BookieDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(ctx.applicationContext, BookieDatabase::class.java, DB_NAME).build()
            }
            return DB_INSTANCE!!
        }
    }
}