package ru.mmn.stopwatch.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mmn.stopwatch.R
import ru.mmn.stopwatch.state.StopwatchStateCalculator
import ru.mmn.stopwatch.state.StopwatchStateHolder
import ru.mmn.stopwatch.model.TimestampProviderImpl
import ru.mmn.stopwatch.utils.ElapsedTimeCalculator
import ru.mmn.stopwatch.viewmodel.StopwatchViewModel
import ru.mmn.stopwatch.viewmodel.StopwatchViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var stopwatchModel: StopwatchViewModel
    private val timestampProvider = TimestampProviderImpl()

    companion object {
        const val TIMER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_time)
        stopwatchModel = StopwatchViewModelFactory(
            stopwatchStateHolder = StopwatchStateHolder(
                StopwatchStateCalculator(
                    timestampProvider,
                    ElapsedTimeCalculator(timestampProvider)
                ),
                ElapsedTimeCalculator(timestampProvider)
            )
        ).create(StopwatchViewModel::class.java)

        stopwatchModel.getLiveData().observe(this) {
            when (it.first) {
                TIMER -> textView.text = it.second
            }
        }

        findViewById<Button>(R.id.button_start).setOnClickListener {
            stopwatchModel.start(TIMER)
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            stopwatchModel.pause(TIMER)
        }
        findViewById<Button>(R.id.button_stop).setOnClickListener {
            stopwatchModel.stop(TIMER)
        }

    }
}

