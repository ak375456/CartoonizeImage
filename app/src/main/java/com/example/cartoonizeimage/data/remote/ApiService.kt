package com.example.cartoonizeimage.data.remote


import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response


import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Headers(
        "x-rapidapi-key: YOUR_KEY_HERE",
        "x-rapidapi-host: cartoon-yourself.p.rapidapi.com",
    )

    @Multipart
    @POST("/facebody/api/portrait-animation/portrait-animation")
    suspend fun cartoonizeImage(
        @Part image: MultipartBody.Part,
        @Part type: MultipartBody.Part
    ): Response<ResponseBody>

}