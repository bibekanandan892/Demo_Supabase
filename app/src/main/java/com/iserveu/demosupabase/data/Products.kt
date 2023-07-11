package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Products(
    @SerialName("corporateID")
    val corporateID: String? = "",
    @SerialName("giftcard")
    val giftcard: Giftcard? = Giftcard(),
    @SerialName("gpr")
    val gpr: Gpr? = Gpr()
)