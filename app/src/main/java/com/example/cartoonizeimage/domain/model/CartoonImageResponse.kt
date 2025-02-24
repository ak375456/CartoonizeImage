package com.example.cartoonizeimage.domain.model

data class CartoonImageResponse(
    val `data`: Data,
    val error_code: Int,
    val error_detail: ErrorDetail,
    val log_id: String,
    val request_id: String
)