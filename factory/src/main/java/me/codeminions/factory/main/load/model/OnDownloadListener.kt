package me.codeminions.factory.main.load.model

interface OnDownloadListener {
    fun onSuccess(info: String)

    fun onFail(info: String)
}