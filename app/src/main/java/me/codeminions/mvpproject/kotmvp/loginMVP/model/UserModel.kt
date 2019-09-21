package me.codeminions.mvpproject.kotmvp.loginMVP.model

import android.os.AsyncTask
import android.os.SystemClock
import me.codeminions.common.bean.User
import java.lang.ref.WeakReference

class UserModel: IUserModel {

    lateinit var listener: OnLoginListener

    override fun login(user: User, listener: OnLoginListener) {
        this.listener = listener
        val task = MyTask(this)
        task.execute(user)
    }

    class MyTask(model: UserModel): AsyncTask<User, Int, String>(){
        var reference: WeakReference<UserModel> = WeakReference(model)

        override fun doInBackground(vararg params: User?): String {

            SystemClock.sleep(3000)

            val user = User("123", "123")

            val model = reference.get()
            if(user.name == params[0]?.name
                    && user.pwd == params[0]?.pwd)
                model?.listener?.onSuccess(user)
            else
                model?.listener?.onFail("Parameter Error")

            return "操作结束"
        }
    }
}