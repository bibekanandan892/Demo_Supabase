package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Email(
    @SerialName("Available")
    val available: Boolean? = null,
    @SerialName("Skip")
    val skip: Boolean? = null
)