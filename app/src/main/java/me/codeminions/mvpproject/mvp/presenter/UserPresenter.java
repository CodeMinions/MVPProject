package me.codeminions.mvpproject.mvp.presenter;

import android.os.Handler;

import me.codeminions.mvpproject.mvp.bean.User;
import me.codeminions.mvpproject.mvp.model.OnLoginListener;
import me.codeminions.mvpproject.mvp.model.UserModel;
import me.codeminions.mvpproject.mvp.view.ILoginView;

public class UserPresenter {
    private ILoginView view;
    private UserModel model;

    private Handler handler = new Handler();

    public UserPresenter(ILoginView view){
        this.view = view;
        model = new UserModel();
    }

    public void login(String name, String pwd){
        User user = new User(name, pwd);

        model.login(user, new OnLoginListener() {
            @Override
            public void onSuccess(User user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.loginSuccess();
                        view.hideLoading();
                    }
                });
            }

            @Override
            public void onFail(String fail) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.loginFail();
                        view.hideLoading();
                    }
                });
            }

            @Override
            public void Error(String error) {

            }
        });
    }

    public void clean(){
        view.cleanUserName();
        view.cleanUserPwd();
    }
}
