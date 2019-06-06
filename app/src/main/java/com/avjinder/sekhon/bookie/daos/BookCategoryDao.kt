package com.avjinder.sekhon.bookie.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avjinder.sekhon.bookie.models.BookCategory

@Dao
interface BookCategoryDao {
    @Query("SELECT * FROM book_category;")
    fun fetchCategories(): LiveData<List<BookCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategories(categories: List<BookCategory>)

    @Query("DELETE FROM book_category;")
    fun clearCategories()
}