package com.example.brewhome.fake.data

import com.example.brewhome.model.Beer
import com.example.brewhome.network.ApiBeer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

object FakeFavoriteDataSource {

    const val beerIdOne = 1
    const val beerNameOne = "Stella Artois"
    const val beerTaglineOne = "Samen genieten van het goed leven, dat is THE LIFE * ARTOIS"
    const val beerImageOne =
        "https://www.prikentik.be/media/catalog/product/s/t/stella_bottle_25cl.png"
    const val beerFirstBrewOne = "1366"
    const val beerAbvOne = 5.2

    const val beerIdTwo = 2
    const val beerTwoOne = "Tout Bien"
    const val beerTaglineTwo = "An average Belgian Pils. Nothing more, nothing less."
    const val beerImageTwo = "https://goedkoopdrank.be/wp-content/uploads/2023/04/GD54014.jpg"
    const val beerFirstBrewTwo = "2021-04-28"
    const val beerAbvTwo = 5.2

    val beers = flow<List<Beer>> {
        listOf(
            ApiBeer(
                id = beerIdOne,
                name = beerNameOne,
                tagline = beerTaglineOne,
                imageUrl = beerImageOne,
                firstBrewed = beerFirstBrewOne,
                abv = beerAbvOne
            ),
            ApiBeer(
                id = beerIdTwo,
                name = beerTwoOne,
                tagline = beerTaglineTwo,
                imageUrl = beerImageTwo,
                firstBrewed = beerFirstBrewTwo,
                abv = beerAbvTwo
            )
        )
    }

    val beer = { id: Int ->
        runBlocking {
            beers
                .map { beerList ->
                    beerList.find { it.id == id }
                }
                .first()
        }
    }
}