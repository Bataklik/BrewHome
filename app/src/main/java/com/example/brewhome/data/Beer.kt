package com.example.brewhome.data

data class Beer(
    val id: Int?,
    val name: String,
    val tagline: String,
    val abv: Double,
    val image_url: String,
    val first_brewed:String
)