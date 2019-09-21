package me.codeminions.common.app

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes
    abstract fun setLayoutId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())

        initWidget()
        intiData()
    }

    open fun initWidget(){

    }

    open fun intiData(){

    }
}