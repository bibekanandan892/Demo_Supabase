package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceSetup(
    @SerialName("MOREFUN")
    val mOREFUN: MOREFUN? = null,
    @SerialName("PAXA910")
    val pAXA910: PAXA910? = null,
    @SerialName("PAXD180")
    val pAXD180: PAXD180? = null,
    @SerialName("WISEASY")
    val wISEASY: WISEASY? = null
)