package com.example.brewhome

import androidx.annotation.StringRes

enum class Destinations (@StringRes val title: Int) {
    Discover(title = R.string.discover),
    Search(title = R.string.search),
    BeerDetail(title = R.string.detail),
}
