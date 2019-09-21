package me.codeminions.common.factory

/**
 * 作为MVP中的公共契约
 */
interface BaseContract {

    interface View<P : Presenter> {
        // 此处设置为可空类型以在后边完成view和presenter的解绑工作
        fun setPresenter(presenter: P?)
    }

    interface Presenter {
        /**
         * 共用的启动操作
         */
        fun start()
        /**
         * 共用的销毁操作
         */
        fun destroy()
    }

}