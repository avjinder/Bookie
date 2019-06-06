package com.avjinder.sekhon.bookie.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.avjinder.sekhon.bookie.daos.BookCategoryDao
import com.avjinder.sekhon.bookie.db.BookieDatabase
import com.avjinder.sekhon.bookie.models.BookCategory
import com.avjinder.sekhon.bookie.networking.NetworkUtils
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BookCategoryRepository(context: Context) {
    private val bookCategoryDao: BookCategoryDao
    private var bookCategoriesCall: Call<List<BookCategory>>? = null

    private val parentJob = Job()
    private val repositoryScope = CoroutineScope(Dispatchers.IO) + parentJob

    init {
        val bookieDatabase = BookieDatabase.createDatabase(context)
        bookCategoryDao = bookieDatabase.bookCategoriesDao()
    }

    fun fetchCategories(): LiveData<List<BookCategory>> {
        fetchCategoriesFromServer()
        Timber.d("Fetching Categories")
        return bookCategoryDao.fetchCategories()
    }

    private fun fetchCategoriesFromServer() {
        val bookCategoriesCallback: Callback<List<BookCategory>> = object : Callback<List<BookCategory>> {

            override fun onResponse(call: Call<List<BookCategory>>, response: Response<List<BookCategory>>) {
                val bookCategories = response.body()
                Timber.d("Response code: ${response.code()}, ${response.isSuccessful}")
                if (bookCategories != null && bookCategories.isNotEmpty()) {
                    Timber.d("Received BookCategories from api, sized: ${bookCategories.size}")
                    saveCategories(bookCategories)
                } else {
                    Timber.d("No BookCategories received from api")
                }
            }

            override fun onFailure(call: Call<List<BookCategory>>, t: Throwable) {
                Timber.d(t, "Failed to fetch BookCategories")
            }
        }
        bookCategoriesCall = NetworkUtils.fetchBookCategories()
        bookCategoriesCall?.enqueue(bookCategoriesCallback)
    }

    fun saveCategories(categories: List<BookCategory>) {
        repositoryScope.launch { bookCategoryDao.saveCategories(categories) }
    }


    fun clearCategories() {
        repositoryScope.launch { bookCategoryDao.clearCategories() }
    }

    fun onCleared() {
        bookCategoriesCall?.cancel()
        repositoryScope.cancel()
    }
}