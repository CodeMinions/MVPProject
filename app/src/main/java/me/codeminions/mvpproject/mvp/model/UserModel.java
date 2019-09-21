package me.codeminions.mvpproject.mvp.model;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.lang.ref.WeakReference;

import me.codeminions.mvpproject.mvp.bean.User;

public class UserModel implements IUserModel {

    private OnLoginListener loginListener;

    @Override
    public void login(User user, OnLoginListener listener) {
        this.loginListener = listener;
        MyTask task = new MyTask(this);
        task.execute(user);
    }

    static class MyTask extends AsyncTask<User, Integer, String> {
        WeakReference<UserModel> reference;

        MyTask(UserModel model) {
            reference = new WeakReference<>(model);
        }

        @Override
        protected String doInBackground(User... users) {
            SystemClock.sleep(3000);

            User user = new User("123", "123");

            UserModel model = reference.get();
            if (users[0].getUsername().equals(user.getUsername())
                    && users[0].getPassword().equals(user.getPassword()))
                model.loginListener.onSuccess(user);
            else
                model.loginListener.onFail("Unknown Error");

            return "操作完成";
        }
    }

    @Override
    public void register() {

    }
}
