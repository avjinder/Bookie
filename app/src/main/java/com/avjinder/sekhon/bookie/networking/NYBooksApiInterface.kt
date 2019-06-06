package com.avjinder.sekhon.bookie.networking

import com.avjinder.sekhon.bookie.models.BookCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYBooksApiInterface {

    @GET("/svc/books/v3/lists/names.json")
    fun fetchBookCategories(@Query("api-key") apiKey: String): Call<List<BookCategory>>
}