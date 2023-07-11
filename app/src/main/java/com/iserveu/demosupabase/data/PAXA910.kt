package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PAXA910(
    @SerialName("Admins")
    val admins: List<String?>? = null,
    @SerialName("Users")
    val users: List<String?>? = null
)