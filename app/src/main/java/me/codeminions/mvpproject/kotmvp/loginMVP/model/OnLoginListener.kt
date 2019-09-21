package me.codeminions.mvpproject.kotmvp.loginMVP.model

import me.codeminions.common.bean.User

interface OnLoginListener{
    fun onSuccess(user: User)

    fun onFail(error: String)
}