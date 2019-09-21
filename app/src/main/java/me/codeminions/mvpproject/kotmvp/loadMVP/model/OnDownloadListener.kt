package me.codeminions.mvpproject.kotmvp.loadMVP.model

interface OnDownloadListener {
    fun onSuccess(info: String)

    fun onFail(info: String)
}