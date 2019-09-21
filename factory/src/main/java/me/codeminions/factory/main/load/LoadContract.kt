package me.codeminions.factory.main.load

import me.codeminions.common.factory.BaseContract
import me.codeminions.common.view.IView

interface LoadContract {
//    interface View: BaseContract.View<Presenter>, IView {
    interface View:  BaseContract.View<BaseContract.Presenter> {
        fun updateProgress(p: Int)

        fun loadSuccess(info: String)
        fun loadFail(info: String)

        fun showLoading()
        fun hideLoading()

    }

    interface Presenter : BaseContract.Presenter {
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
}