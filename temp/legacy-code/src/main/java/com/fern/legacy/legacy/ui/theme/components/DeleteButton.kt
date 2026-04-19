package com.fern.wallet.ui.theme.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.fern.ui.R
import com.fern.wallet.ui.theme.GradientRed
import com.fern.wallet.ui.theme.White

@Deprecated("Old design system. Use `:fern-design` and Material3")
@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    hasShadow: Boolean = true,
    onClick: () -> Unit,
) {
    IvyCircleButton(
        modifier = modifier
            .size(48.dp)
            .testTag("delete_button"),
        backgroundPadding = 6.dp,
        icon = R.drawable.ic_delete,
        backgroundGradient = GradientRed,
        enabled = true,
        hasShadow = hasShadow,
        tint = White,
        onClick = onClick
    )
}
