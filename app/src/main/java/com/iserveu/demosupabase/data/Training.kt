package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Training(
    @SerialName("aeps")
    val aeps: List<Aep?>? = null,
    @SerialName("bbps")
    val bbps: List<Bbp?>? = null,
    @SerialName("common_onboarding")
    val commonOnboarding: List<CommonOnboarding?>? = null,
    @SerialName("demat")
    val demat: List<Demat?>? = null,
    @SerialName("device")
    val device: List<Device?>? = null,
    @SerialName("dmt")
    val dmt: List<Dmt?>? = null,
    @SerialName("insurance")
    val insurance: List<Insurance?>? = null,
    @SerialName("matm")
    val matm: List<Matm?>? = null,
    @SerialName("pos")
    val pos: List<Po?>? = null,
    @SerialName("recharge")
    val recharge: List<Recharge?>? = null,
    @SerialName("upi")
    val upi: List<Upi?>? = null,
    @SerialName("wallet")
    val wallet: List<Wallet?>? = null
)