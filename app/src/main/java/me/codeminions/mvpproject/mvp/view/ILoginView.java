package me.codeminions.mvpproject.mvp.view;

public interface ILoginView {
    String getUserName();
    String getUserPwd();

    void showLoading();
    void hideLoading();

    void loginSuccess();
    void loginFail();

    void cleanUserName();
    void cleanUserPwd();
}
