package com.iserveu.demosupabase.data.package_info


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Table(
    @SerialName("package_details")
    val package_details: String? = null,
    @SerialName("package_name")
    val package_name: String? = null
)