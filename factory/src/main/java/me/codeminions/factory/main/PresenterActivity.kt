package me.codeminions.factory.main

import android.os.Bundle
import me.codeminions.common.app.BaseActivity
import me.codeminions.common.factory.BaseContract
import me.codeminions.factory.main.load.LoadContract
import me.codeminions.factory.main.login.LoginContract

abstract class PresenterActivity : BaseActivity(), LoginContract.View, LoadContract.View {

    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPresenter()
    }

    override fun setPresenter(presenter: BaseContract.Presenter?) {
        this.mPresenter = presenter as MainPresenter
    }

    abstract fun initPresenter()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.destroy()
    }
}