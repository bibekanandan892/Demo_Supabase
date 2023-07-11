package com.iserveu.demosupabase.repository

import android.util.Log
import com.iserveu.demosupabase.ApiService
import com.iserveu.demosupabase.data.PackageInfoData
import com.iserveu.demosupabase.data.ResponseModel
import com.iserveu.demosupabase.data.UserDto
import com.iserveu.demosupabase.data.package_info.Table
import com.iserveu.demosupabase.utils.NetworkResult
import com.iserveu.demosupabase.utils.handleResponse
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class Repository @Inject constructor(
    private val httpClient: HttpClient,
    private val postgrest: Postgrest,
    private val apiService: ApiService
) {
    fun uploadImage(
        imageByteArray: ByteArray, bucket: String,
        filename: String,
    ): Flow<NetworkResult<ResponseModel>> {
        return handleResponse {
            apiService.uploadImage(authorization = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndzemRpaWN6Y296YXdkand1bm5xIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY4Nzg0MzkwOCwiZXhwIjoyMDAzNDE5OTA4fQ.wbXJ4uuTw7EpIpmrN_vhvzbLo12fmTfVVmSpMKoTa6A", filename = filename, bucket = bucket, image = imageByteArray)
        }
    }

    suspend fun insetPackageInfo(packageName : String, packageInfoData: PackageInfoData ): Boolean {
        return try {
            val packageInfoDataString = Json {
                ignoreUnknownKeys = true
            }.encodeToString(packageInfoData)
            val packageInfo = Table(
                package_name  = packageName,
                package_details = packageInfoDataString
            )
            postgrest["AdminNameManagement"].insert(value = packageInfo, upsert = true)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun updateUserProfile(
        name: String,
        email: String,
    ) {

        try {
            postgrest["user"].update(
                {
                    UserDto::phone setTo "New number"
                    UserDto::email setTo email
                    UserDto::address setTo "New Address"
                }
            ) {
                UserDto::name eq name
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteUserProfile(
        name: String,
        email: String,
    ) {

        try {
            postgrest["user"].delete {
                UserDto::name eq name
                UserDto::name eq email
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getPackageInfo(
        packageName: String,
    ) {

        try {
            val result = postgrest["AdminNameManagement"].select(filter = {
                    Table::package_name eq packageName
            }).decodeList<Table>()[0].package_details

            val packageData = result?.let {
                Json { ignoreUnknownKeys = true }.decodeFromString<PackageInfoData>(
                    it
                )
            }
            Log.d("packageData", packageData.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}