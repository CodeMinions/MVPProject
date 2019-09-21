package me.codeminions.factory.main

import android.os.Handler
import me.codeminions.common.bean.User
import me.codeminions.factory.main.load.LoadContract
import me.codeminions.factory.main.load.model.DownloadModel
import me.codeminions.factory.main.load.model.OnDownloadListener
import me.codeminions.factory.main.login.LoginContract
import me.codeminions.factory.main.login.model.OnLoginListener
import me.codeminions.factory.main.login.model.UserModel

class MainPresenter(var view: PresenterActivity?) : LoginContract.Presenter, LoadContract.Presenter {

    private val userModel = UserModel()
    private var loadModel = DownloadModel(this)

    var handler = Handler()

    init {
        view?.setPresenter(this)
    }

    override fun login(userName: String, userPwd: String) {
        val user = User(userName, userPwd)
        start()

        userModel.login(user, object : OnLoginListener {
            override fun onSuccess(user: User) {
                handler.post {
                    view?.onLoginSuccess(user)
                    view?.hideLogining()
                    view?.showButton()
                }
            }

            override fun onFail(error: String) {
                handler.post {
                    view?.onLoginFail(error)
                    view?.hideLogining()
                    view?.showButton()
                }
            }
        })
    }

    override fun clean() {
        view?.cleanUserName()
        view?.cleanUserPwd()
    }

    override fun start() {
        view?.showLogining()
        view?.hideButton()
    }

    override fun destroy() {
        val mView = view
        view = null
        mView?.setPresenter(null)
    }

    override fun load(content: String) {
        view?.showLoading()
        view?.hideButton()

        loadModel.load(content, object : OnDownloadListener {
            override fun onSuccess(info: String) {
                handler.post {
                    view?.loadSuccess(info)
                    view?.hideLoading()
                    view?.showButton()
                }
            }

            override fun onFail(info: String) {
                handler.post {
                    view?.loadFail(info)
                    view?.hideLoading()
                    view?.showButton()
                }
            }
        })
    }

    override fun updateProgress(progress: Int) {
        view?.updateProgress(progress)
    }

    override fun cancel(pic: String) {
        loadModel.cancel(pic)
    }

}
