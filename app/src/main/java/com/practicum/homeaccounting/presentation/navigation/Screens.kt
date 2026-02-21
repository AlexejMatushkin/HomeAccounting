package com.practicum.homeaccounting.presentation.navigation

sealed class Screens(val route: String) {
    data object Welcome : Screens("welcome")
    data object Main : Screens("main")
    data object AddProduct : Screens("add_product")
}
