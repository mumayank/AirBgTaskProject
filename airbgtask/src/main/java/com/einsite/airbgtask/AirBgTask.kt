package com.einsite.airbgtask

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AirBgTask {

    interface Callback {
        fun doTaskInBackground(): Boolean
        fun onSuccess()
        fun onFailure(reasonString: String)
    }

    companion object {
        const val TASK_SUCCESSFUL = true
        const val TASK_NOT_SUCCESSFUL = false

        fun newTask(callback: Callback?) {
            doAsync {
                try {
                    val result = callback?.doTaskInBackground() ?: false
                    uiThread {
                        if (result == TASK_SUCCESSFUL) {
                            callback?.onSuccess()
                        } else {
                            callback?.onFailure("error: doTaskInBackground() returned TASK_NOT_SUCCESSFUL")
                        }
                    }
                } catch (t: Throwable) {
                    uiThread {
                        callback?.onFailure("error: $t")
                    }
                }
            }
        }
    }

}