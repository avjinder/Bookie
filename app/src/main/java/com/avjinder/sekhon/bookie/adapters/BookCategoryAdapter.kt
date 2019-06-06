package com.avjinder.sekhon.bookie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avjinder.sekhon.bookie.R
import com.avjinder.sekhon.bookie.holders.BookCategoryItemHolder
import com.avjinder.sekhon.bookie.models.BookCategory

class BookCategoryAdapter(private var bookCategoryList: List<BookCategory>) :
    RecyclerView.Adapter<BookCategoryItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCategoryItemHolder =
        BookCategoryItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_book_category_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = bookCategoryList.size

    override fun onBindViewHolder(holder: BookCategoryItemHolder, position: Int) {
        holder.bind(bookCategoryList[position])
    }

    fun updateItems(newCategories: List<BookCategory>) {
        bookCategoryList = newCategories
        notifyDataSetChanged()
    }
}