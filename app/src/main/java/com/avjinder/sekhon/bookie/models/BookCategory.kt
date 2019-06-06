package com.avjinder.sekhon.bookie.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "book_category")
class BookCategory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(
        name = "category_name",
        typeAffinity = ColumnInfo.TEXT
    ) @SerializedName("list_name") val categoryName: String = "",
    @ColumnInfo(
        name = "display_name",
        typeAffinity = ColumnInfo.TEXT
    ) @SerializedName("display_name") val categoryDisplayName: String = "",
    @ColumnInfo(
        name = "encoded_name",
        typeAffinity = ColumnInfo.TEXT
    ) @SerializedName("encoded_name") val categoryEncodedName: String = "",
    @ColumnInfo(
        name = "oldest_pub_date",
        typeAffinity = ColumnInfo.TEXT
    ) @SerializedName("oldest_published_date") val oldestPublishedDate: String = "",
    @ColumnInfo(
        name = "newest_pub_date",
        typeAffinity = ColumnInfo.TEXT
    ) @SerializedName("newest_published_date") val newestPublishedDate: String = "",
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT) @SerializedName("updated") val updated: String = ""
)
