package com.example.cartoonizeimage.domain.use_cases.upload_image_use_case

import com.example.cartoonizeimage.domain.model.CartoonImageResponse
import com.example.cartoonizeimage.domain.repository.ImageRepository
import com.example.cartoonizeimage.util.Resource
import java.io.File
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val cartoonImageRepository: ImageRepository
) {
    suspend operator fun invoke (imageFile:File,style:String):Resource<CartoonImageResponse>{
        return cartoonImageRepository.uploadImage(imageFile,style)
    }
}