package me.codeminions.mvpproject.kotmvp.loadMVP.presenter

interface ILoadPresenter {
    /**
     * 下载
     * @param content 下载地址
     */
    fun load(content: String)

    /**
     * 更新下载进度
     * @param progress 下载进度
     */
    fun updateProgress(progress: Int)

    /**
     * 取消下载
     * @param pic 取消对象
     */
    fun cancel(pic: String)
}