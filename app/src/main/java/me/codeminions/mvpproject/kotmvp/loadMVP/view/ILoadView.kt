package me.codeminions.mvpproject.kotmvp.loadMVP.view

import me.codeminions.mvpproject.kotmvp.IView

interface ILoadView : IView {
    fun updateProgress(p: Int)

    fun loadSuccess(info: String)
    fun loadFail(info: String)

    fun showLoading()
    fun hideLoading()
}
