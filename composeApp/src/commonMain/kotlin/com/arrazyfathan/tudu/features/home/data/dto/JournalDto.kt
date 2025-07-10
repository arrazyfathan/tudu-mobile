package com.arrazyfathan.tudu.features.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JournalDto(
    @SerialName("category")
    val category: Category? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("createdAt")
    val createdAt: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("tags")
    val tags: List<Tag> = emptyList(),
    @SerialName("title")
    val title: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null,
) {
    @Serializable
    data class Category(
        @SerialName("id")
        val id: String? = null,
        @SerialName("name")
        val name: String? = null,
    )

    @Serializable
    data class Tag(
        @SerialName("id")
        val id: String? = null,
        @SerialName("name")
        val name: String? = null,
    )
}
