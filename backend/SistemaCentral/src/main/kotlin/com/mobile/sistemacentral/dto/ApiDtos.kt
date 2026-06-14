package com.mobile.sistemacentral.dto

data class LoginRequest(
    val username: String,
    val senha: String
)

data class LoginResponse(
    val token: Int,
    val mensagem: String
)

data class VotoRequest(
    val token: Int,
    val filmeId: Int,
    val diretorId: Int
)

data class MessageResponse(
    val mensagem: String
)