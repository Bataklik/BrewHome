package com.bataklik.brewhome.fake.data

import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.network.ApiBeer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * De fake favorite data voor de unit tests.
 */
object FakeFavoriteDataSource {

    private const val BEER_ID_ONE = 1
    private const val BEER_NAME_ONE = "Stella Artois"
    private const val BEER_TAGLINE_ONE =
        "Samen genieten van het goed leven, dat is THE LIFE * ARTOIS"
    private const val BEER_IMAGE_ONE =
        "https://www.prikentik.be/media/catalog/product/s/t/stella_bottle_25cl.png"
    private const val BEER_FIRST_BREW_ONE = "1366"
    private const val BEER_ABV_ONE = 5.2

    private const val BEER_ID_TWO = 2
    private const val BEER_NAME_TWO = "Tout Bien"
    private const val BEER_TAGLINE_TWO = "An average Belgian Pils. Nothing more, nothing less."
    private const val BEER_IMAGE_TWO =
        "https://goedkoopdrank.be/wp-content/uploads/2023/04/GD54014.jpg"
    private const val BEER_FIRST_BREW_TWO = "2021-04-28"
    private const val BEER_ABV_TWO = 5.2

    val BEERS = flow<List<Beer>> {
        listOf(
            ApiBeer(
                id = BEER_ID_ONE,
                name = BEER_NAME_ONE,
                tagline = BEER_TAGLINE_ONE,
                imageUrl = BEER_IMAGE_ONE,
                firstBrewed = BEER_FIRST_BREW_ONE,
                abv = BEER_ABV_ONE
            ),
            ApiBeer(
                id = BEER_ID_TWO,
                name = BEER_NAME_TWO,
                tagline = BEER_TAGLINE_TWO,
                imageUrl = BEER_IMAGE_TWO,
                firstBrewed = BEER_FIRST_BREW_TWO,
                abv = BEER_ABV_TWO
            )
        )
    }

    val BEER_BY_ID = { id: Int ->
        runBlocking {
            BEERS
                .map { beerList ->
                    beerList.find { it.id == id }
                }
                .first()
        }
    }
}