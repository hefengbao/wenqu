package com.hefengbao.wenqu.data.model

import com.hefengbao.wenqu.data.database.entity.PoemSentenceEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoemSentence(
    val id: Long,
    val content: String,
    val from: String,
    @SerialName("poem_id")
    val poemId: Long?
)

fun PoemSentence.toPoemSentenceEntity() = PoemSentenceEntity(
    id, content, from, poemId
)
