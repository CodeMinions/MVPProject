package me.codeminions.mvpproject.kotmvp.loginMVP.model

import me.codeminions.common.bean.User

interface IUserModel{

    fun login(user: User, listener: OnLoginListener)
}