package com.arrazyfathan.tudu.features.home.data.mapper

import com.arrazyfathan.tudu.features.home.data.dto.JournalDto
import com.arrazyfathan.tudu.features.home.domain.model.Journal

fun JournalDto.toDomain(): Journal =
    Journal(
        id = this.id.orEmpty(),
        title = this.title.orEmpty(),
        content = this.content.orEmpty(),
        category = this.category?.toDomain(),
        tags = this.tags.map { it.toDomain() },
        date = this.date.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
    )

fun JournalDto.Category.toDomain(): Journal.Category =
    Journal.Category(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
    )

fun JournalDto.Tag.toDomain(): Journal.Tag =
    Journal.Tag(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
    )
