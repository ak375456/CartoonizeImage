package com.example.cartoonizeimage.presenetation

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.cartoonizeimage.presenetation.util.StyleSelector
import com.example.cartoonizeimage.util.Resource
import java.io.File

@Composable
fun CartoonizeScreen(viewModel: CartoonizeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val imageState by viewModel.imageState.collectAsState()
    val selectedStyle by viewModel.selectedStyle.collectAsState()

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = uri
                // Convert URI to File and upload the image
                val imageFile = File(context.cacheDir, "selected_image.jpg")
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    imageFile.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
                Log.d("DEBUGGING",selectedStyle.name)
                viewModel.uploadImage(imageFile)
            }
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            StyleSelector(
                selectedStyle = selectedStyle,
                onStyleSelected = { viewModel.setSelectedStyle(it) }
            )
            imageUri?.let { uri ->
                Image(
                    painter = rememberImagePainter(data = uri),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(onClick = { launcher.launch("image/*") }) {
                Text(text = "Select Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (imageState) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    val cartoonImageUrl = (imageState as Resource.Success).data.data.image_url
                    AsyncImage(
                        model = cartoonImageUrl,
                        contentDescription = "Cartoonized Image",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                is Resource.Error -> {
                    Text(text = "Error: ${(imageState as Resource.Error).message}")
                }

                else -> {}
            }
        }
    }
}