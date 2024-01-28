package me.mqn.mytestplace.data.dto

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("email_address")
    val email: String,
    val password: String,
)