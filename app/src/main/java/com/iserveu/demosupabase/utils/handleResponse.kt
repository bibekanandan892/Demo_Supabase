package com.iserveu.demosupabase.utils

import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.io.IOException
import com.iserveu.demosupabase.utils.NetworkResult
import retrofit2.Response

inline fun <reified T> handleResponse(crossinline call: suspend () -> Response<T>): Flow<NetworkResult<T>> {
    return flow {
        emit(NetworkResult.Loading())
        try {
            val response = call.invoke().body()
            emit(NetworkResult.Success(response))
        } catch (e: ClientRequestException) {
            val errorMessage = getErrorDes(errorKeys = listOf("error_description","message","statusDesc"), errorString = e.response.body())
            if(errorMessage != null){
                emit(NetworkResult.Error(errorMessage))
            }else{
                emit(NetworkResult.Error(e.response.status.description))
            }
        } catch (e: ServerResponseException) {
            val errorMessage = getErrorDes(errorKeys = listOf("error_description","message","statusDesc"), errorString = e.response.body())
            if(errorMessage != null){
                emit(NetworkResult.Error(errorMessage))
            }else{
                emit(NetworkResult.Error(e.response.status.description))
            }
        } catch (e: RedirectResponseException) {
            val errorMessage = getErrorDes(errorKeys = listOf("error_description","message","statusDesc"), errorString = e.response.body())
            if(errorMessage != null){
                emit(NetworkResult.Error(errorMessage))
            }else{
                emit(NetworkResult.Error(e.response.status.description))
            }
        }catch (e: ResponseException) {
            val errorMessage = getErrorDes(errorKeys = listOf("error_description","message","statusDesc"), errorString = e.response.body())
            if(errorMessage != null){
                emit(NetworkResult.Error(errorMessage))
            }else{
                emit(NetworkResult.Error(e.response.status.description))
            }
        } catch (e: ConnectTimeoutException) {
            emit(NetworkResult.Error("Connection Timeout"))
        } catch (e: SocketTimeoutException) {
            emit(NetworkResult.Error("Socket Timeout"))
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.message ?: "Unknown IO Error"))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown Error"))
        }
    }
}


fun getErrorDes(errorKeys : List<String>,errorString : String): String?{
    val errorObj = JSONObject(errorString)
    errorKeys.forEach { errorKey ->
        if(errorObj.has(errorKey)){
            return errorObj.getString(errorKey).toString()
        }
    }
    return null
}