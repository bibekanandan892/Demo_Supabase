package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Banner(
    @SerialName("Type")
    val type: String? = null,
    @SerialName("Url")
    val url: String? = null
)