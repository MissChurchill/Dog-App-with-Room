package com.example.doggone.network

import com.squareup.moshi.Json


data class DogPhoto(
    @Json(name = "message")
    val imgUrl: String?

    )


