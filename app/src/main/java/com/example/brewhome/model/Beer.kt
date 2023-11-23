package com.example.brewhome.model

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val imageUrl: String,
    val abv: Double,
)