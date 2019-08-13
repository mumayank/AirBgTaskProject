package com.einsite.airbgtaskproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.einsite.airbgtask.AirBgTask
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    object Counter {
        var counter = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.GONE

        button.setOnClickListener {
            button.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            AirBgTask.newTask(object: AirBgTask.Callback {
                override fun doTaskInBackground(): Boolean {
                    Thread.sleep(1000)
                    Counter.counter++
                    return true
                }

                override fun onSuccess() {
                    button.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    textView.text = Counter.counter.toString()
                }

                override fun onFailure(reasonString: String) {
                    // will never occur in this scenario
                }
            })

        }
    }
}
