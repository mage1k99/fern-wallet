package com.fern.design.api

import com.fern.base.legacy.Theme
import com.fern.design.IvyContext
import com.fern.design.l0_system.IvyColors
import com.fern.design.l0_system.IvyShapes
import com.fern.design.l0_system.IvyTypography

@Deprecated("Old design system. Use `:fern-design` and Material3")
interface IvyDesign {
    @Deprecated("Old design system. Use `:fern-design` and Material3")
    fun context(): IvyContext

    @Deprecated("Old design system. Use `:fern-design` and Material3")
    fun typography(): IvyTypography

    @Deprecated("Old design system. Use `:fern-design` and Material3")
    fun colors(theme: Theme, isDarkModeEnabled: Boolean): IvyColors

    @Deprecated("Old design system. Use `:fern-design` and Material3")
    fun shapes(): IvyShapes
}
