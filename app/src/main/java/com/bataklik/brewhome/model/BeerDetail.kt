package com.bataklik.brewhome.model

/**
 * Dataklasse die een gedetailleerd bierobject vertegenwoordigt.
 *
 * @property id Unieke identificatie voor het bier.
 * @property name Naam van het bier.
 * @property tagline Korte tagline of beschrijving van het bier.
 * @property firstBrewed Datum waarop het bier voor het eerst werd gebrouwen.
 * @property description Beschrijving van het bier.
 * @property imageUrl URL naar de afbeelding van het bier.
 * @property abv Alcoholpercentage van het bier op volume (ABV).
 * @property ibu International Bitterness Units (IBU) van het bier, kan null zijn.
 * @property attenuationLevel Attenuatie niveau van het bier, kan null zijn.
 * @property targetFg Doel einddichtheid van het bier, kan null zijn.
 * @property targetOg Doel oorspronkelijke dichtheid van het bier, kan null zijn.
 * @property ebc Kleur van het bier in European Brewery Convention (EBC) eenheden, kan null zijn.
 * @property srm Kleur van het bier in Standard Reference Method (SRM) eenheden, kan null zijn.
 * @property ph pH-waarde van het bier, kan null zijn.
 * @property foodPairing Lijst van voedselcombinaties die goed passen bij het bier.
 * @property brewersTips Tips van de brouwer voor het bier.
 * @property contributedBy Bijdrage van de gebruiker aan het bier.
 */
data class BeerDetail(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
    val abv: Double,
    val ibu: Double?,
    val attenuationLevel: Double?,
    val targetFg: Int?,
    val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
)


/**
 * Extensiefunctie om een [BeerDetail] object om te zetten naar een [Beer] object.
 * @return [Beer] object met geselecteerde eigenschappen van het oorspronkelijke [BeerDetail] object.
 */
fun BeerDetail
    .asBeer(): Beer = Beer(
    id = id,
    name = name,
    tagline = tagline,
    imageUrl = imageUrl,
    firstBrewed = firstBrewed,
    abv = abv
)

