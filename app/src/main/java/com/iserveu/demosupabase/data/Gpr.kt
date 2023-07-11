package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gpr(
    @SerialName("physical")
    val physical: Physical? = Physical(),
    @SerialName("virtual")
    val virtual: VirtualX? = VirtualX()
)