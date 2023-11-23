sealed class Screen(val route: String) {
    object Discover : Screen("discover") {
        override fun toString(): String {
            return "Discover beers"
        }
    }

    object Search : Screen("search"){
        override fun toString(): String {
            return "Search"
        }
    }
    data class BeerDetail(val beerId: Int? = null) : Screen("beerDetail"){
        override fun toString(): String {
            return "Beer detail"
        }
    }
}
