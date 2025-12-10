package com.mysticbyte.memecreatorapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform