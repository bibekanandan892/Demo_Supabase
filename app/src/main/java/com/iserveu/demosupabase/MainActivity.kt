package com.iserveu.demosupabase

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iserveu.demosupabase.data.PackageInfoData
import com.iserveu.demosupabase.repository.Repository
import com.iserveu.demosupabase.ui.theme.DemoSupabaseTheme
import com.iserveu.demosupabase.utils.NetworkResult
import com.iserveu.demosupabase.utils.data
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotiations.SupabaseExperimental
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import io.ktor.client.call.body
import io.ktor.http.headers
import io.ktor.http.headersOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: Repository

    @OptIn(SupabaseExperimental::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            val imageUploadLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
                    if (bitmap != null) {
                        scope.launch {

                            repository.uploadImage(imageByteArray = bitmap.toByteArray(), filename = "${UUID.randomUUID().toString()}.png", bucket = "bibekananda").collectLatest {networkResult->
                            when(networkResult){
                                is NetworkResult.Error -> {
                                    Toast.makeText(context,"error" +networkResult.message, Toast.LENGTH_SHORT).show()
                                    Log.d("upload Image", "onCreate: ${networkResult.message}")
                                }
                                is NetworkResult.Loading -> {
                                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                                    Log.d("upload Image", "onCreate: LOading")

                                }
                                is NetworkResult.Success -> {
                                    Toast.makeText(context, "success"+networkResult.data.toString(), Toast.LENGTH_SHORT).show()
                                    Log.d("upload Image", "onCreate: ${networkResult.data}")

                                }
                            }

                            }
//
                        }
                    } else {
                        Toast.makeText(context, "Select Image with size 200KB", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // Launch the camera to capture an image

                        scope.launch {
                            val packageInfo = Json {
                                ignoreUnknownKeys = true
                            }.decodeFromString<PackageInfoData>(data)
                           repository.insetPackageInfo(packageName = "com.bibekanandan892.kotak_compose_multi_module", packageInfoData = packageInfo)
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("inset PackageInfo")
                }
                Button(
                    onClick = {
                        // Launch the camera to capture an image
                        scope.launch {
                           repository.getPackageInfo(
                              packageName = "com.kotak.kotakpro"
                           )
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Get PackageInfo")
                }
                Button(
                    onClick = {
                        // Launch the camera to capture an image

                        scope.launch {

                           repository.deleteUserProfile( name ="Bibekananda Nayak",
                               email = "UpdateEmail@gmail.com")
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("delete User")
                }
                Button(
                    onClick = {
                        // Launch the camera to capture an image
//                        imageUploadLauncher.launch(null)
                        Toast.makeText(this@MainActivity, data, Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Take Picture")
                }
            }
        }
    }

    private fun getClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndzemRpaWN6Y296YXdkand1bm5xIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY4Nzg0MzkwOCwiZXhwIjoyMDAzNDE5OTA4fQ.wbXJ4uuTw7EpIpmrN_vhvzbLo12fmTfVVmSpMKoTa6A",
//            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndzemRpaWN6Y296YXdkand1bm5xIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODc4NDM5MDgsImV4cCI6MjAwMzQxOTkwOH0.dtXBm3XVF2hmNSHNjJI6IikYA44QcqYSNtLVSqgxf0Y",
            supabaseUrl = "https://wszdiiczcozawdjwunnq.supabase.co"
        ) {
            install(Storage) {
            }

        }
    }
}


fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

