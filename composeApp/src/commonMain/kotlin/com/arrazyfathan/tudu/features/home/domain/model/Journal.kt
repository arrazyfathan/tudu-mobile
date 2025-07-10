package com.arrazyfathan.tudu.features.home.domain.model

data class Journal(
    val category: Category?,
    val content: String,
    val createdAt: String,
    val date: String,
    val id: String,
    val tags: List<Tag>?,
    val title: String,
    val updatedAt: String,
) {
    data class Category(
        val id: String,
        val name: String,
    )

    data class Tag(
        val id: String,
        val name: String,
    )
}
