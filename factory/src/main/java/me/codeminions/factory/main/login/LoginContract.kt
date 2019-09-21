package me.codeminions.factory.main.login

import me.codeminions.common.bean.User
import me.codeminions.common.factory.BaseContract
import me.codeminions.common.view.IView

interface LoginContract {
    interface View :BaseContract.View<BaseContract.Presenter>, IView {

        fun onLoginSuccess(user: User)
        fun onLoginFail(info: String)

        fun getUserName(): String
        fun getUserPwd(): String

        fun cleanUserName()
        fun cleanUserPwd()

        /**
         * 进度条操作
         */
        fun showLogining()
        fun hideLogining()
    }

    interface Presenter : BaseContract.Presenter {
        /**
         * 触发一次登录
         */
        fun login(userName: String, userPwd: String)

        fun clean()
    }

}