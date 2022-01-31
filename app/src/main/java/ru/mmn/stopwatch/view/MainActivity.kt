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
        const val TIMER_1 = 1
        const val TIMER_2 = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                TIMER_1 -> findViewById<TextView>(R.id.text_time_1).text = it.second
                TIMER_2 -> findViewById<TextView>(R.id.text_time_2).text = it.second
            }
        }

        findViewById<Button>(R.id.button_start_1).setOnClickListener {
            stopwatchModel.start(TIMER_1)
        }
        findViewById<Button>(R.id.button_pause_1).setOnClickListener {
            stopwatchModel.pause(TIMER_1)
        }
        findViewById<Button>(R.id.button_stop_1).setOnClickListener {
            stopwatchModel.stop(TIMER_1)
        }
        findViewById<Button>(R.id.button_start_2).setOnClickListener {
            stopwatchModel.start(TIMER_2)
        }
        findViewById<Button>(R.id.button_pause_2).setOnClickListener {
            stopwatchModel.pause(TIMER_2)
        }
        findViewById<Button>(R.id.button_stop_2).setOnClickListener {
            stopwatchModel.stop(TIMER_2)
        }

    }
}

