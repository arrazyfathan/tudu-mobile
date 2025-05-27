package com.arrazyfathan.tudu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform