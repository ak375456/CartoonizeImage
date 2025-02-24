package com.example.cartoonizeimage.domain.model

data class ErrorDetail(
    val code: String,
    val code_message: String,
    val message: String,
    val status_code: Int
)