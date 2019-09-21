package me.codeminions.factory.main.login.model

import me.codeminions.common.bean.User

interface IUserModel{

    fun login(user: User, listener: OnLoginListener)
}