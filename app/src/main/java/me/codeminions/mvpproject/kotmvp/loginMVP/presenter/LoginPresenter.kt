package me.codeminions.mvpproject.kotmvp.loginMVP.presenter

import android.os.Handler
import me.codeminions.common.bean.User
import me.codeminions.mvpproject.kotmvp.loginMVP.model.OnLoginListener
import me.codeminions.mvpproject.kotmvp.loginMVP.model.UserModel
import me.codeminions.mvpproject.kotmvp.loginMVP.view.ILoginView


class LoginPresenter(val view: ILoginView) {

    private var userModel: UserModel = UserModel()
    private var handler = Handler()

    fun login(userName: String, userPwd: String) {
        val user = User(userName, userPwd)
        view.showLogining()
        view.hideButton()
        userModel.login(user, object: OnLoginListener {
            override fun onSuccess(user: User) {
                handler.post{
                    view.onLoginSuccess(user)
                    view.hideLogining()
                    view.showButton()
                }
            }

            override fun onFail(error: String) {
                handler.post{
                    view.onLoginFail(error)
                    view.hideLogining()
                    view.showButton()
                }
            }
        })
    }

    fun clean() {
        view.cleanUserPwd()
        view.cleanUserName()
    }

}