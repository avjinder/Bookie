package com.avjinder.sekhon.bookie.utils

import com.avjinder.sekhon.bookie.models.BookCategory
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import timber.log.Timber

class BookCategoryTypeAdapter : TypeAdapter<List<BookCategory>>() {

    init {
        Timber.tag("BOOKIE")
    }

    override fun read(jr: JsonReader?): List<BookCategory>? {
        Timber.d("Reading json")
        jr ?: return null
        var bookCategoriesList: ArrayList<BookCategory> = arrayListOf()
        jr.beginObject()

        var fieldName = ""
        while (jr.hasNext()) {
            val token = jr.peek()
            when {
                token == JsonToken.BEGIN_ARRAY && fieldName == "results" -> {
                    Timber.d("Json Array starting")
                    bookCategoriesList = handleArray(jr)
                }
                token == JsonToken.NAME -> {
                    fieldName = jr.nextName()
                    if (fieldName != "results") {
                        jr.skipValue()
                    }
                }
            }
        }
        jr.endObject()
        Timber.d("BookCategories parsed size: ${bookCategoriesList.size}")
        return bookCategoriesList.toList()
    }

    private fun handleArray(reader: JsonReader): ArrayList<BookCategory> {
        val categoriesList: ArrayList<BookCategory> = arrayListOf()
        reader.beginArray()
        val gson = Gson()
        Timber.d("Handling array begins")

        arrayLoop@ while (true) {
            when (reader.peek()) {
                JsonToken.END_ARRAY -> {
                    Timber.d("Handling array ends")
                    reader.endArray()
                    break@arrayLoop
                }
                JsonToken.BEGIN_OBJECT -> {
                    try {
                        val bc = gson.fromJson<BookCategory>(reader, BookCategory::class.java)
                        Timber.d("BookCategory: ${bc.categoryName}")
                        categoriesList.add(bc)
                    } catch (jpe: JsonParseException) {
                        Timber.d(jpe)
                    }
                }
                JsonToken.END_OBJECT -> reader.endObject()
                else -> reader.skipValue()
            }
        }
        return categoriesList
    }


    override fun write(out: JsonWriter?, value: List<BookCategory>?) {
        // not implemented, since we're only receiving data from api
    }
}