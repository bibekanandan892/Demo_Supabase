package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Onboard(
    @SerialName("Aadhaar")
    val aadhaar: Aadhaar? = null,
    @SerialName("Admins")
    val admins: List<String?>? = null,
    @SerialName("BasicInfo")
    val basicInfo: BasicInfo? = null,
    @SerialName("Email")
    val email: Email? = null,
    @SerialName("Mobile")
    val mobile: Mobile? = null,
    @SerialName("Pan")
    val pan: Pan? = null,
    @SerialName("ShowOnBoard")
    val showOnBoard: String? = null,
    @SerialName("Users")
    val users: List<String?>? = null
)