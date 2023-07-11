package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZendeskForm(
    @SerialName("AEPS")
    val aEPS: String? = null,
    @SerialName("BBPS")
    val bBPS: String? = null,
    @SerialName("DEVICE")
    val dEVICE: String? = null,
    @SerialName("DMT")
    val dMT: String? = null,
    @SerialName("INSURANCE")
    val iNSURANCE: String? = null,
    @SerialName("MATM")
    val mATM: String? = null,
    @SerialName("OTHER")
    val oTHER: String? = null,
    @SerialName("POS")
    val pOS: String? = null,
    @SerialName("RECHARGE")
    val rECHARGE: String? = null,
    @SerialName("UPI")
    val uPI: String? = null,
    @SerialName("WALLET")
    val wALLET: String? = null
)