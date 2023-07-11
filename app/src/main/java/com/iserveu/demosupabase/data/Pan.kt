package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pan(
    @SerialName("Available")
    val available: Boolean? = null,
    @SerialName("Sequence")
    val sequence: String? = null,
    @SerialName("Skip")
    val skip: Boolean? = null
)