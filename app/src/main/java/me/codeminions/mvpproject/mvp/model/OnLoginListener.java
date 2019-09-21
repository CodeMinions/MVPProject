package me.codeminions.mvpproject.mvp.model;

import me.codeminions.mvpproject.mvp.bean.User;

public interface OnLoginListener {
    public void onSuccess(User user);

    public void onFail(String fail);

    public void Error(String error);
}
