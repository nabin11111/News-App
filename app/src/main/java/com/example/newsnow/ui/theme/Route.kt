package com.example.newsnow.ui.theme

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
object HomePageScreen
@Serializable
data class NewsArticleScreen(
    val url : String
)