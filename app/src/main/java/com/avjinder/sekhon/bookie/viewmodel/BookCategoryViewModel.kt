package com.avjinder.sekhon.bookie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.avjinder.sekhon.bookie.models.BookCategory
import com.avjinder.sekhon.bookie.repository.BookCategoryRepository

class BookCategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val bookCategoryRepository: BookCategoryRepository = BookCategoryRepository(application)
    var bookCategories: LiveData<List<BookCategory>> = bookCategoryRepository.fetchCategories()

    fun saveCategories(categories: List<BookCategory>) = bookCategoryRepository.saveCategories(categories)
    fun clearCategories() = bookCategoryRepository.clearCategories()

    override fun onCleared() {
        super.onCleared()
        bookCategoryRepository.onCleared()
    }
}