package me.codeminions.mvpproject

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Button
import me.codeminions.mvpproject.contractMVP.MainActivity
import me.codeminions.mvpproject.mvp.app.BaseActivity

class LaunchActivity : BaseActivity() {

    private lateinit var drawable: ColorDrawable
    private lateinit var root: View
    private lateinit var enter: Button

    override fun getLayoutRes(): Int {
        return R.layout.activity_launch
    }

    override fun initWidget() {
        root = findViewById(R.id.root)
        enter = findViewById(R.id.btn_enter)

        val color = resources.getColor(R.color.colorPrimaryDark, null)
        val colorDrawable = ColorDrawable(color)

        root.background = colorDrawable
        drawable = colorDrawable

        enter.setOnClickListener { realSkip() }
    }

    override fun initData() {
        startAnim(0.5f, Runnable {
            skip()
        })
    }

    private fun skip() {
        startAnim(1f, Runnable {
            enterAnim()
        })
    }

    private fun realSkip() {
        MainActivity.startAction(this)
        finish()
    }

    private fun startAnim(progress: Float, callBack: Runnable) {
        val finalColor = Color.parseColor("#ffffff")
        val argbEvaluator = ArgbEvaluator()
        val endColor = argbEvaluator.evaluate(progress, drawable.color, finalColor) as Int

        val valueAnimator = ObjectAnimator.ofObject(root,
                "backgroundColor",
                argbEvaluator,
                drawable.color, endColor)
        valueAnimator.duration = 2000
        valueAnimator.setIntValues(drawable.color, endColor)
        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                callBack.run()
            }
        })
        valueAnimator.start()
    }

    private fun enterAnim() {
        enter.animate()
                .setDuration(1000)
                .translationY(0f)
                .setInterpolator(AnticipateInterpolator(2f))
                .rotation(720f)
                .start()
    }

}