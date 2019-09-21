package me.codeminions.mvpproject.kotmvp.loadMVP.presenter

import android.os.Handler
import me.codeminions.mvpproject.kotmvp.loadMVP.model.DownloadModel
import me.codeminions.mvpproject.kotmvp.loadMVP.model.OnDownloadListener
import me.codeminions.mvpproject.kotmvp.loadMVP.view.ILoadView

class LoadPresenter(var loadView: ILoadView): ILoadPresenter {

    private val loadModel: DownloadModel = DownloadModel(this)
    private val handler = Handler()

    override fun load(content: String) {
        loadView.showLoading()
        loadView.hideButton()

        loadModel.load(content, object: OnDownloadListener {
            override fun onSuccess(info: String) {
                handler.post{
                    loadView.loadSuccess(info)

                    loadView.hideLoading()
                    loadView.showButton()
                }
            }

            override fun onFail(info: String) {
                handler.post{
                    loadView.loadFail(info)

                    loadView.hideLoading()
                    loadView.showButton()
                }
            }
        })
    }

    override fun updateProgress(progress: Int) {
        loadView.updateProgress(progress)
    }

    override fun cancel(pic: String) {
        loadModel.cancel(pic)
    }
}