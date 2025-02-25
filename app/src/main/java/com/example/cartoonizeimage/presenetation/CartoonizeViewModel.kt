package com.example.cartoonizeimage.presenetation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cartoonizeimage.domain.model.CartoonImageResponse
import com.example.cartoonizeimage.domain.use_cases.upload_image_use_case.UploadImageUseCase
import com.example.cartoonizeimage.util.CartoonStyle
import com.example.cartoonizeimage.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CartoonizeViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {

    // UI state
    private val _imageState = MutableStateFlow<Resource<CartoonImageResponse>>(Resource.Idle)
    val imageState: StateFlow<Resource<CartoonImageResponse>> = _imageState

    private val _selectedStyle = MutableStateFlow(CartoonStyle.CLASSIC_CARTOON)
    val selectedStyle: StateFlow<CartoonStyle> = _selectedStyle

    fun setSelectedStyle(style: CartoonStyle) {
        _selectedStyle.value = style
    }

    // Function to upload the image
    fun uploadImage(imageFile: File) {
        viewModelScope.launch {
            val style = _selectedStyle.value
            Log.d("DEBUGGINGVM", "Uploading image with style: ${style.value}")
            _imageState.value = Resource.Loading
            _imageState.value = uploadImageUseCase(imageFile, style.value)
        }
    }
    // Zarorat nahi iski
//    fun resetState() {
//        _imageState.value = Resource.Idle
//    }
}