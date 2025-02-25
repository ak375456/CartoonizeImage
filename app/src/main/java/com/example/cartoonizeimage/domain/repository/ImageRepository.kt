package com.example.cartoonizeimage.domain.repository

import com.example.cartoonizeimage.domain.model.CartoonImageResponse
import com.example.cartoonizeimage.util.Resource
import java.io.File

interface ImageRepository {
    suspend fun uploadImage(imageFile:File,style:String): Resource<CartoonImageResponse>
}