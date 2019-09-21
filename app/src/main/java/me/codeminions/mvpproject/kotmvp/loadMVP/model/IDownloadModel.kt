package me.codeminions.mvpproject.kotmvp.loadMVP.model

interface IDownloadModel {
    fun load(pic: String, listener: OnDownloadListener)

    fun cancel(pic: String)
}