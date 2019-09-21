package me.codeminions.mvpproject.kotmvp.loadMVP.model

import android.os.AsyncTask
import android.os.SystemClock
import me.codeminions.factory.main.load.LoadContract
import me.codeminions.mvpproject.kotmvp.loadMVP.presenter.LoadPresenter
import java.lang.ref.WeakReference

const val length = 5

class DownloadModel(val presenter: LoadPresenter) : IDownloadModel {

    lateinit var listener: OnDownloadListener
    private lateinit var task: MyTask

    override fun load(pic: String, listener: OnDownloadListener) {
        this.listener = listener

        task = MyTask(this)
        task.execute(pic)
    }

    override fun cancel(pic: String) {
        task.cancel(true)
    }

    class MyTask(downModel: DownloadModel) : AsyncTask<String, Int, Boolean>() {
        private var reference = WeakReference<DownloadModel>(downModel)
        private var progress = 0

        override fun doInBackground(vararg params: String?): Boolean {
            var i = 0
            val model = reference.get()

            while (i < length) {
                if (isCancelled){
                    model?.listener?.onFail("User Cancel")
                    return false
                }

                SystemClock.sleep(1000)
                i++
                progress = i*(100/ length)
                publishProgress(progress)
            }
            model?.listener?.onSuccess("${params[0]} load success")

            return true
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val model = reference.get()
            model?.presenter?.updateProgress(progress)
        }
    }

}