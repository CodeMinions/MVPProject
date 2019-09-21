package me.codeminions.mvpproject.kotmvp

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.ViewTarget
import me.codeminions.mvpproject.R
import me.codeminions.common.app.BaseActivity
import me.codeminions.common.bean.User
import me.codeminions.mvpproject.kotmvp.loadMVP.presenter.LoadPresenter
import me.codeminions.mvpproject.kotmvp.loadMVP.view.ILoadView
import me.codeminions.mvpproject.kotmvp.loginMVP.presenter.LoginPresenter
import me.codeminions.mvpproject.kotmvp.loginMVP.view.ILoginView

class MainActivity : BaseActivity(), ILoginView, ILoadView {

    private lateinit var edName: EditText
    private lateinit var edPwd: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnLoad: Button
    private lateinit var btnClean: TextView

    private lateinit var linear: LinearLayout

    private lateinit var logining: ProgressBar
    private lateinit var loading: ProgressBar

    private var loginPresenter: LoginPresenter = LoginPresenter(this)
    private var loadPresenter: LoadPresenter = LoadPresenter(this)

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initWidget() {
        edName = findViewById(R.id.edit_phone)
        edPwd = findViewById(R.id.edit_password)
        btnClean = findViewById(R.id.txt_go_register)
        logining = findViewById(R.id.load)
        loading = findViewById(R.id.loading)
        btnLogin = findViewById(R.id.btn_submit)
        btnLoad = findViewById(R.id.btn_load)

        linear = findViewById(R.id.bg_im)

        btnClean.setOnClickListener { loginPresenter.clean() }
        // 点击进度条取消加载
        loading.setOnClickListener { loadPresenter.cancel("LOGIN PIC") }

        Glide.with(this)
                .load(R.drawable.bg_src_tianjin)
                .centerCrop()
                .into(object: ViewTarget<LinearLayout, GlideDrawable>(linear){
                    override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                        var drawable = resource!!.current
                        drawable = DrawableCompat.wrap(drawable)

                        drawable.setColorFilter(Color.parseColor("#1572fc"), PorterDuff.Mode.SCREEN)
                        this.view.background = drawable
                    }
                })
    }

    fun onClickLogin(v: View) {
        when (v.id) {
            R.id.btn_submit ->
                loginPresenter.login(getUserName(), getUserPwd())
            R.id.btn_load ->
                loadPresenter.load("LOGIN PIC")
        }
    }

    override fun showButton() {
        btnLogin.visibility = View.VISIBLE
        btnLoad.visibility = View.VISIBLE
    }

    override fun hideButton() {
        btnLogin.visibility = View.GONE
        btnLoad.visibility = View.GONE
    }


    /**
     * 加载模块
     */
    override fun updateProgress(p: Int) {
        loading.progress = p
        Log.i("task", "" + p)
    }

    override fun loadSuccess(info: String) {
        Toast.makeText(this, "Success ($info)", Toast.LENGTH_SHORT).show()
    }

    override fun loadFail(info: String) {
        Toast.makeText(this, "Fail ($info)", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    /**
     * 登录模块
     */
    override fun onLoginSuccess(user: User) {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginFail(error: String) {
        Toast.makeText(this, "Login Fail ($error)", Toast.LENGTH_SHORT).show()
    }

    override fun getUserName(): String {
        return edName.text.toString()
    }

    override fun getUserPwd(): String {
        return edPwd.text.toString()
    }

    override fun cleanUserName() {
        edName.setText("")
    }

    override fun cleanUserPwd() {
        edPwd.setText("")
    }

    override fun showLogining() {
        logining.visibility = View.VISIBLE
    }

    override fun hideLogining() {
        logining.visibility = View.GONE
    }
}