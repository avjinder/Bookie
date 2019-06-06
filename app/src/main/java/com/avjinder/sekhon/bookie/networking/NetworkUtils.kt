package com.avjinder.sekhon.bookie.networking

import com.avjinder.sekhon.bookie.BuildConfig
import com.avjinder.sekhon.bookie.models.BookCategory
import com.avjinder.sekhon.bookie.utils.BookCategoryTypeAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.HttpUrl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    private const val NY_TIMES_DEV_API_KEY = BuildConfig.ApiKey
    private const val SCHEME = "https"
    private const val HOST = "api.nytimes.com"

    private val retrofit: Retrofit
        get() {

            val bookCategoryListType = TypeToken.getParameterized(List::class.java, BookCategory::class.java).type
            val gson = GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(bookCategoryListType, BookCategoryTypeAdapter())
                .create()

            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttp = okhttp3.OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .client(okHttp)
                .baseUrl(baseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

    private fun booksApi(): NYBooksApiInterface = retrofit.create(NYBooksApiInterface::class.java)

    private fun baseUrl(): HttpUrl = HttpUrl.Builder()
        .scheme(SCHEME)
        .host(HOST)
        .build()


    fun fetchBookCategories(): Call<List<BookCategory>> = booksApi().fetchBookCategories(NY_TIMES_DEV_API_KEY)
}