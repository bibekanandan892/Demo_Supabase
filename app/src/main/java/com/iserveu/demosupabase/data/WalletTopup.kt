package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WalletTopup(
    @SerialName("BankChallan")
    val bankChallan: BankChallan? = BankChallan(),
    @SerialName("CashDiposit")
    val cashDiposit: CashDiposit? = CashDiposit(),
    @SerialName("UPI")
    val uPI: UPIX? = UPIX()
)