package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Giftcard(
    @SerialName("physical")
    val physical: Physical? = null,
    @SerialName("virtual")
    val virtual: Virtual? = null
)