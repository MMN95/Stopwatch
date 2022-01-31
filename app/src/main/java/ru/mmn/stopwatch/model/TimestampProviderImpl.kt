package ru.mmn.stopwatch.model

class TimestampProviderImpl: TimestampProvider {
    override fun getMilliseconds(): Long = System.currentTimeMillis()
}