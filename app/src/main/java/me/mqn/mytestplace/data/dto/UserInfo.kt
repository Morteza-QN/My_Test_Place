package me.mqn.mytestplace.data.dto

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val id: String,
    @SerializedName("email_address")
    val email: String,
)