package me.codeminions.mvpproject.kotmvp.loginMVP.view

import me.codeminions.mvpproject.kotmvp.IView
import me.codeminions.common.bean.User

interface ILoginView : IView {
    fun onLoginSuccess(user: User)
    fun onLoginFail(error: String)

    fun getUserName(): String
    fun getUserPwd(): String

    fun cleanUserName()
    fun cleanUserPwd()

    fun showLogining()
    fun hideLogining()
}