package me.codeminions.factory.main.login.model

import me.codeminions.common.bean.User

interface OnLoginListener{
    fun onSuccess(user: User)

    fun onFail(error: String)
}