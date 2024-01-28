package me.mqn.mytestplace.common.base

interface CoroutinesErrorHandler {

    fun onError(message: String)
}