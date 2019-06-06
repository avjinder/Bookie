package com.avjinder.sekhon.bookie.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avjinder.sekhon.bookie.R
import com.avjinder.sekhon.bookie.models.BookCategory

class BookCategoryItemHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val titleTextView by lazy { itemView.findViewById<TextView>(R.id.book_category_item_title) }

    fun bind(bookCategory: BookCategory) {
        titleTextView.text = bookCategory.categoryName
    }
}