package com.fern.home.customerjourney

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.fern.base.time.TimeProvider
import com.fern.design.l0_system.Gradient
import com.fern.domain.RootScreen
import com.fern.legacy.IvyWalletCtx
import com.fern.navigation.Navigation
import com.fern.poll.data.PollRepository

@Immutable
data class CustomerJourneyCardModel(
    val id: String,
    @Suppress("MaximumLineLength", "ParameterWrapping", "MaxLineLength", "ParameterListWrapping")
    val condition: suspend (trnCount: Long, plannedPaymentsCount: Long, ivyContext: IvyWalletCtx, deps: CustomerJourneyDeps) -> Boolean,
    val title: String,
    val description: String,
    val cta: String?,
    @DrawableRes val ctaIcon: Int,

    val hasDismiss: Boolean = true,

    val background: Gradient,
    val onAction: (Navigation, IvyWalletCtx, RootScreen) -> Unit
)

@Immutable
data class CustomerJourneyDeps(
    val pollRepository: PollRepository,
    val timeProvider: TimeProvider,
)
