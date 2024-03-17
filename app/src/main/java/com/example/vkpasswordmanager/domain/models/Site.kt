package com.example.vkpasswordmanager.domain.models

data class Site(
    val id: Long?,
    val name: String,
    val url: String,
    val password: String,
    var mostUsed: Int
)
