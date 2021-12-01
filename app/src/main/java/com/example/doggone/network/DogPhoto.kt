package com.example.doggone.network

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "DogImages")
data class DogPhoto(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @NonNull @ColumnInfo(name = "image_url")
    @Json(name = "message")
    val imageUrl: String?,
    @ColumnInfo(name="previous_url")
    val prevUrl: String?,
    @ColumnInfo(name="favorite_dogs")
    val favDogPhoto:String?,
    )


