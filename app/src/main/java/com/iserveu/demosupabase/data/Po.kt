package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Po(
    @SerialName("content")
    val content: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("video")
    val video: String? = null
)