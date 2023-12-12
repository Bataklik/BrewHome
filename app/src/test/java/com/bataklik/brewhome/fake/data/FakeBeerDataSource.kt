package com.bataklik.brewhome.fake.data

import com.bataklik.brewhome.network.ApiBeer
import com.bataklik.brewhome.network.ApiBeerDetail

object FakeBeerDataSource {
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

    val beers = listOf(
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

    val beerDetail = ApiBeerDetail(
        id = beerIdOne,
        name = beerNameOne,
        tagline = beerTaglineOne,
        imageUrl = beerImageOne,
        firstBrewed = beerFirstBrewOne,
        abv = beerAbvOne,
        description = "Stella Artois nodigt iedereen uit om samen van het leven te genieten. Een unieke smaak, perfect om van te genieten, om te delen en om een toast uit te brengen op het goede leven. The Life Artois viert de Belgische levensstijl die volledig draait om genieten van de kleine dingen, samen met onze geliefden! Serveer en geniet van de meer dan 600 jaar rijke Belgische brouwtraditie van Stella Artois.",
        contributedBy = "Artois familie",
        attenuationLevel = 2.0,
        brewersTips = "Malt",
        ph = 4.0,
        ebc = 6.0,
        foodPairing = listOf("Cheese"),
        ibu = 24.0,
        srm = 3.0,
        targetFg = 9,
        targetOg = 2.9
    )
}