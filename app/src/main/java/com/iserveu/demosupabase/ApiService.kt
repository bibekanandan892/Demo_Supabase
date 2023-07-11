package com.iserveu.demosupabase

import android.graphics.Bitmap
import com.iserveu.demosupabase.data.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("object/{bucket}/{filename}")
    suspend fun uploadImage(
        @Header("Authorization") authorization: String,
        @Path("bucket") bucket: String,
        @Path("filename") filename: String,
        @Body image: ByteArray
    ): Response<ResponseModel>
}