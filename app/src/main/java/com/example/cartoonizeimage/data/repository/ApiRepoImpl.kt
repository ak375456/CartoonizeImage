package com.example.cartoonizeimage.data.repository


import com.example.cartoonizeimage.data.remote.ApiService
import com.example.cartoonizeimage.domain.model.CartoonImageResponse
import com.example.cartoonizeimage.domain.repository.ImageRepository
import com.example.cartoonizeimage.util.Resource
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import java.io.File
import javax.inject.Inject


class ApiRepoImpl @Inject constructor(
    private val apiService: ApiService
) : ImageRepository {

    override suspend fun uploadImage(imageFile: File,style:String): Resource<CartoonImageResponse> {
        return try {
            // Convert File to MultipartBody.Part
            val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
            val typePart = MultipartBody.Part.createFormData("type", style)

            // Call the API
            val response = apiService.cartoonizeImage(imagePart,typePart)

            if (response.isSuccessful) {
                // Parse the response body into CartoonImageResponse
                val cartoonImageResponse = parseResponse(response.body())
                Resource.Success(cartoonImageResponse)

            } else {

                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Resource.Error("API call failed: $errorBody")
            }
        } catch (e: Exception) {
            Resource.Error("An error occurred: ${e.message}")
        }
    }

    private fun parseResponse(responseBody: ResponseBody?): CartoonImageResponse {
        if (responseBody == null) {
            throw IllegalArgumentException("Response body is null")
        }

        return Gson().fromJson(responseBody.charStream(), CartoonImageResponse::class.java)
    }
}