package com.iserveu.demosupabase.data

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val error : String? = null,

)