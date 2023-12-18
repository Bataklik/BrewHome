sealed class Screen(val route: String) {
    object Discover : Screen("discover") {
        override fun toString(): String {
            return "Discover"
        }
    }

    object Search : Screen("search"){
        override fun toString(): String {
            return "Search"
        }
    }
    object BeerDetail: Screen("beerDetail"){
        override fun toString(): String {
            return "Beer detail"
        }
    }
}
