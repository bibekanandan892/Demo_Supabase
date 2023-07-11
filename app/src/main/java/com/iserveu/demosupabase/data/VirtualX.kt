package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VirtualX(
    @SerialName("pid")
    val pid: Int? = null,
    @SerialName("purl")
    val purl: String? = null
)