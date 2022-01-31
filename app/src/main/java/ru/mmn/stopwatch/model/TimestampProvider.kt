package ru.mmn.stopwatch.model

interface TimestampProvider {
    fun getMilliseconds(): Long
}