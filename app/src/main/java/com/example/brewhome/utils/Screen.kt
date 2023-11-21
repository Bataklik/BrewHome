sealed class Screen(val route: String) {
    object Discover : Screen("discover")
    object Search : Screen("search")
    data class BeerDetail(val beerId: Int? = null) : Screen("beerDetail")}
