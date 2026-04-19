package com.fern.base.di

import com.fern.base.resource.AndroidResourceProvider
import com.fern.base.resource.ResourceProvider
import com.fern.base.threading.DispatchersProvider
import com.fern.base.threading.IvyDispatchersProvider
import com.fern.base.time.TimeConverter
import com.fern.base.time.TimeProvider
import com.fern.base.time.impl.DeviceTimeProvider
import com.fern.base.time.impl.StandardTimeConverter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BaseHiltBindings {
    @Binds
    fun dispatchersProvider(impl: IvyDispatchersProvider): DispatchersProvider

    @Binds
    fun bindTimezoneProvider(impl: DeviceTimeProvider): TimeProvider

    @Binds
    fun bindTimeConverter(impl: StandardTimeConverter): TimeConverter

    @Binds
    fun resourceProvider(impl: AndroidResourceProvider): ResourceProvider
}