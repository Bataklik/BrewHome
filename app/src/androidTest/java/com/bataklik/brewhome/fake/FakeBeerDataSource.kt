package com.bataklik.brewhome.fake

import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail


/**
 * Valse data voor de AndroidTesten
 */
object FakeBeerDataSource {
    private const val BEER_ID_ONE = 1
    const val BEER_NAME_ONE = "Stella Artois"
    const val BEER_TAGLINE_ONE =
        "Samen genieten van het goed leven, dat is THE LIFE * ARTOIS"
    private const val BEER_IMAGE_ONE =
        "https://www.prikentik.be/media/catalog/product/s/t/stella_bottle_25cl.png"
    const val BEER_FIRST_BREW_ONE = "1366"
    private const val BEER_ABV_ONE = 5.2
    private const val BEER_IBU_ONE = 0.0
    private const val BEER_PH_ONE = 0.1
    private const val BEER_SRM_ONE = 0.2
    private const val BEER_EBC_ONE = 0.3
    private const val BEER_TARGETFG_ONE = 4
    private const val BEER_TARGETOG_ONE = 0.5
    private const val BEER_ATTENTIONLEVEL_ONE = 0.6
    const val BEER_DESCRIPTION_ONE =
        "Stella Artois is een Belgisch pilsbier van lage gisting met een alcoholpercentage van 5,2%. Het bier wordt gebrouwen door Anheuser-Busch InBev te Leuven. Het bier wordt wereldwijd verkocht en is het belangrijkste exportproduct van België."
    private const val BEER_BREWINGTIPS_ONE =
        "Serveer Stella Artois op een temperatuur van 3 graden Celsius. Zo komt de smaak het beste tot zijn recht."

    private const val BEER_CONTRIBUTEDBY_ONE = "STELLA ARTOIS"

    private const val BEER_FOODPARING_ONE = "Gegrilde kip"


    private const val BEER_ID_TWO = 2
    private const val BEER_NAME_TWO = "Tout Bien"
    private const val BEER_TAGLINE_TWO = "An average Belgian Pils. Nothing more, nothing less."
    private const val BEER_IMAGE_TWO =
        "https://goedkoopdrank.be/wp-content/uploads/2023/04/GD54014.jpg"
    private const val BEER_FIRST_BREW_TWO = "2021-04-28"
    private const val BEER_ABV_TWO = 5.2

    val IS_FAVORITE_TRUE = { _: Int ->
        true
    }
    val IS_FAVORITE_FALSE = { _: Int ->
        false
    }

    val BEER = BeerDetail(
        id = BEER_ID_ONE,
        name = BEER_NAME_ONE,
        tagline = BEER_TAGLINE_ONE,
        imageUrl = BEER_IMAGE_ONE,
        firstBrewed = BEER_FIRST_BREW_ONE,
        abv = BEER_ABV_ONE,
        ibu = BEER_IBU_ONE,
        ph = BEER_PH_ONE,
        srm = BEER_SRM_ONE,
        ebc = BEER_EBC_ONE,
        targetFg = BEER_TARGETFG_ONE,
        targetOg = BEER_TARGETOG_ONE,
        attenuationLevel = BEER_ATTENTIONLEVEL_ONE,
        description = BEER_DESCRIPTION_ONE,
        brewersTips = BEER_BREWINGTIPS_ONE,
        contributedBy = BEER_CONTRIBUTEDBY_ONE,
        foodPairing = listOf(
            BEER_FOODPARING_ONE,
        )
    )
    val BEERS = listOf(
        Beer(
            id = BEER_ID_ONE,
            name = BEER_NAME_ONE,
            tagline = BEER_TAGLINE_ONE,
            imageUrl = BEER_IMAGE_ONE,
            firstBrewed = BEER_FIRST_BREW_ONE,
            abv = BEER_ABV_ONE
        ),
        Beer(
            id = BEER_ID_TWO,
            name = BEER_NAME_TWO,
            tagline = BEER_TAGLINE_TWO,
            imageUrl = BEER_IMAGE_TWO,
            firstBrewed = BEER_FIRST_BREW_TWO,
            abv = BEER_ABV_TWO
        )
    )
}