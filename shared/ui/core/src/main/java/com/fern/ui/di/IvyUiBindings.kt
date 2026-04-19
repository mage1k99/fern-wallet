package com.fern.ui.di

import com.fern.ui.time.DevicePreferences
import com.fern.ui.time.TimeFormatter
import com.fern.ui.time.impl.AndroidDateTimePicker
import com.fern.ui.time.impl.AndroidDevicePreferences
import com.fern.ui.time.impl.DateTimePicker
import com.fern.ui.time.impl.IvyTimeFormatter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface IvyUiBindings {
    @Binds
    fun timeFormatter(impl: IvyTimeFormatter): TimeFormatter

    @Binds
    fun deviceTimePreferences(impl: AndroidDevicePreferences): DevicePreferences

    @Binds
    fun dateTimePicker(impl: AndroidDateTimePicker): DateTimePicker
}