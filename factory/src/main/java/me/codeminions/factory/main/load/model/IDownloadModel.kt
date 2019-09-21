package me.codeminions.factory.main.load.model

interface IDownloadModel {
    fun load(pic: String, listener: OnDownloadListener)

    fun cancel(pic: String)
}