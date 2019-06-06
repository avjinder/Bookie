package com.avjinder.sekhon.bookie.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avjinder.sekhon.bookie.R
import com.avjinder.sekhon.bookie.adapters.BookCategoryAdapter
import com.avjinder.sekhon.bookie.models.BookCategory
import com.avjinder.sekhon.bookie.viewmodel.BookCategoryViewModel
import timber.log.Timber

class BookCategoriesActivity : AppCompatActivity() {

    private lateinit var bookCategoryViewModel: BookCategoryViewModel
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.main_recycler_view) }
    private lateinit var adapter: BookCategoryAdapter
    private var bookCategoryList: List<BookCategory> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = BookCategoryAdapter(bookCategoryList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        ContextCompat.getDrawable(this, android.R.drawable.divider_horizontal_bright)?.let { dr ->
            val did = DividerItemDecoration(this, RecyclerView.VERTICAL)
            did.setDrawable(dr)
            recyclerView.addItemDecoration(did)
        }

        bookCategoryViewModel = ViewModelProviders.of(this).get(BookCategoryViewModel::class.java)
        bookCategoryViewModel.bookCategories.observe(this, Observer { categories ->
            Timber.d("Received Book Category list, size: ${categories.size}")
            bookCategoryList = categories
            updateAdapter(bookCategoryList)
        })
    }

    private fun updateAdapter(categories: List<BookCategory>) {
        adapter.updateItems(categories)
    }
}
