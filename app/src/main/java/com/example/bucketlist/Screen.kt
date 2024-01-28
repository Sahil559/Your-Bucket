package com.example.bucketlist

sealed class Screen(val route:String) {
    object HomeScreen: Screen("home_screen")
    object EditScreen: Screen("edit_screen")

}