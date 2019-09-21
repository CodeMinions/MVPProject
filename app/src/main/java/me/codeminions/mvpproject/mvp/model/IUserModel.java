package me.codeminions.mvpproject.mvp.model;

import me.codeminions.mvpproject.mvp.bean.User;

public interface IUserModel {

    public void login(User user, OnLoginListener listener);

    public void register();
}
