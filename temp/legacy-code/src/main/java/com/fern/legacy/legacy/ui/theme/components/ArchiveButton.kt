package com.fern.wallet.ui.theme.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.fern.design.l0_system.UI
import com.fern.ui.R
import com.fern.wallet.ui.theme.Gradient
import com.fern.wallet.ui.theme.GradientIvy
import com.fern.wallet.ui.theme.White

@Composable
fun ArchiveButton(
    isArchived: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IvyCircleButton(
        modifier = modifier
            .size(48.dp)
            .testTag("archive_button"),
        backgroundPadding = 6.dp,
        icon = R.drawable.ic_hide_m,
        backgroundGradient = if (isArchived) GradientIvy else Gradient.solid(UI.colors.gray),
        enabled = true,
        hasShadow = true,
        tint = White,
        onClick = onClick
    )
}
