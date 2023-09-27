package com.huanchengfly.tieba.post.ui.common.theme.compose

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.huanchengfly.tieba.post.App
import com.huanchengfly.tieba.post.R
import com.huanchengfly.tieba.post.rememberPreferenceAsState
import com.huanchengfly.tieba.post.utils.ThemeUtil
import com.huanchengfly.tieba.post.utils.appPreferences
import com.huanchengfly.tieba.post.utils.compose.darken

@Stable
data class ExtendedColors(
    val theme: String,
    val isNightMode: Boolean,
    val primary: Color = Color.Unspecified,
    val accent: Color = Color.Unspecified,
    val onAccent: Color = Color.Unspecified,
    val topBar: Color = Color.Unspecified,
    val onTopBar: Color = Color.Unspecified,
    val onTopBarSecondary: Color = Color.Unspecified,
    val onTopBarActive: Color = Color.Unspecified,
    val topBarSurface: Color = Color.Unspecified,
    val onTopBarSurface: Color = Color.Unspecified,
    val bottomBar: Color = Color.Unspecified,
    val bottomBarSurface: Color = Color.Unspecified,
    val onBottomBarSurface: Color = Color.Unspecified,
    val text: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val textOnPrimary: Color = Color.Unspecified,
    val textDisabled: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val chip: Color = Color.Unspecified,
    val onChip: Color = Color.Unspecified,
    val unselected: Color = Color.Unspecified,
    val card: Color = Color.Unspecified,
    val floorCard: Color = Color.Unspecified,
    val divider: Color = Color.Unspecified,
    val shadow: Color = Color.Unspecified,
    val indicator: Color = Color.Unspecified,
    val windowBackground: Color = Color.Unspecified,
    val placeholder: Color = Color.Unspecified,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        ThemeUtil.THEME_DEFAULT,
        false,
    )
}


@SuppressLint("ConflictingOnColor")
fun getColorPalette(
    darkTheme: Boolean,
    extendedColors: ExtendedColors
): Colors {
    val primaryColor = extendedColors.accent
    val secondaryColor = extendedColors.primary
    return if (darkTheme) {
        darkColors(
            primary = primaryColor,
            primaryVariant = primaryColor.darken(),
            secondary = secondaryColor,
            secondaryVariant = Color(0xFF3F310A),
            onSecondary = Color(0xFFFFFFFF),
            background = extendedColors.background,
            onBackground = extendedColors.text,
            surface = extendedColors.card,
        )
    } else {
        lightColors(
            primary = primaryColor,
            primaryVariant = primaryColor.darken(),
            secondary = secondaryColor,
            secondaryVariant = Color(0xFF000000),
            onSecondary = Color(0xFFFFFFFF),
            background = extendedColors.background,
            onBackground = extendedColors.text,
            surface = extendedColors.card,
        )
    }
}

@Composable
private fun getDynamicColor(
    isDarkColorPalette: Boolean,
    tonalPalette: TonalPalette,
): ExtendedColors {
    return if (isDarkColorPalette) {
        getDarkDynamicColor(tonalPalette)
    } else {
        getLightDynamicColor(tonalPalette)
    }
}

@Composable
private fun getDynamicTopBarColor(
    tonalPalette: TonalPalette,
    isNightMode: Boolean,
): Color {
    val topBarUsePrimaryColor =
        LocalContext.current.appPreferences.toolbarPrimaryColor && !isNightMode
    val primaryColor = if (isNightMode) {
        tonalPalette.primary80
    } else {
        tonalPalette.primary40
    }
    val backgroundColor = if (isNightMode) {
        tonalPalette.neutralVariant10
    } else {
        tonalPalette.neutralVariant99
    }
    return if (topBarUsePrimaryColor) {
        primaryColor
    } else {
        backgroundColor
    }
}

@Composable
private fun getDynamicOnTopBarColor(
    tonalPalette: TonalPalette,
    isNightMode: Boolean,
): Color {
    val topBarUsePrimaryColor =
        LocalContext.current.appPreferences.toolbarPrimaryColor && !isNightMode
    val onPrimaryColor = if (isNightMode) {
        tonalPalette.primary20
    } else {
        tonalPalette.primary100
    }
    val onBackgroundColor = if (isNightMode) {
        tonalPalette.neutralVariant90
    } else {
        tonalPalette.neutralVariant10
    }
    return if (topBarUsePrimaryColor) {
        onPrimaryColor
    } else {
        onBackgroundColor
    }
}

@Composable
private fun getDynamicOnTopBarSecondaryColor(
    tonalPalette: TonalPalette,
    isNightMode: Boolean,
): Color {
    val topBarUsePrimaryColor =
        LocalContext.current.appPreferences.toolbarPrimaryColor && !isNightMode
    val primaryColor = if (isNightMode) {
        tonalPalette.primary20
    } else {
        tonalPalette.primary80
    }
    val backgroundColor = if (isNightMode) {
        tonalPalette.neutralVariant70
    } else {
        tonalPalette.neutralVariant40
    }
    return if (topBarUsePrimaryColor) {
        primaryColor
    } else {
        backgroundColor
    }
}

@Composable
private fun getDynamicOnTopBarActiveColor(
    tonalPalette: TonalPalette,
    isNightMode: Boolean,
): Color {
    val topBarUsePrimaryColor =
        LocalContext.current.appPreferences.toolbarPrimaryColor && !isNightMode
    val primaryColor = if (isNightMode) {
        tonalPalette.primary0
    } else {
        tonalPalette.primary100
    }
    val backgroundColor = if (isNightMode) {
        tonalPalette.neutralVariant100
    } else {
        tonalPalette.neutralVariant0
    }
    return if (topBarUsePrimaryColor) {
        primaryColor
    } else {
        backgroundColor
    }
}

@Composable
private fun getDynamicTopBarSurfaceColor(
    tonalPalette: TonalPalette,
    isNightMode: Boolean,
): Color {
    val topBarUsePrimaryColor =
        LocalContext.current.appPreferences.toolbarPrimaryColor && !isNightMode
    val primaryColor = if (isNightMode) {
        tonalPalette.primary30
    } else {
        tonalPalette.primary90
    }
    val backgroundColor = if (isNightMode) {
        tonalPalette.neutralVariant20
    } else {
        tonalPalette.neutralVariant95
    }
    return if (topBarUsePrimaryColor) {
        primaryColor
    } else {
        backgroundColor
    }
}

@Composable
private fun getDynamicOnTopBarSurfaceColor(
    tonalPalette: TonalPalette,
    isNightMode: Boolean,
): Color {
    val topBarUsePrimaryColor =
        LocalContext.current.appPreferences.toolbarPrimaryColor && !isNightMode
    val primaryColor = if (isNightMode) {
        tonalPalette.primary90
    } else {
        tonalPalette.primary10
    }
    val backgroundColor = if (isNightMode) {
        tonalPalette.neutralVariant70
    } else {
        tonalPalette.neutralVariant30
    }
    return if (topBarUsePrimaryColor) {
        primaryColor
    } else {
        backgroundColor
    }
}

@Composable
private fun getLightDynamicColor(tonalPalette: TonalPalette): ExtendedColors {
    return ExtendedColors(
        theme = "dynamic",
        isNightMode = false,
        primary = tonalPalette.primary40,
        textOnPrimary = tonalPalette.primary90,
        accent = tonalPalette.secondary40,
        onAccent = tonalPalette.secondary100,
        topBar = getDynamicTopBarColor(tonalPalette, false),
        onTopBar = getDynamicOnTopBarColor(tonalPalette, false),
        onTopBarSecondary = getDynamicOnTopBarSecondaryColor(tonalPalette, false),
        onTopBarActive = getDynamicOnTopBarActiveColor(tonalPalette, false),
        topBarSurface = getDynamicTopBarSurfaceColor(tonalPalette, false),
        onTopBarSurface = getDynamicOnTopBarSurfaceColor(tonalPalette, false),
        bottomBar = tonalPalette.neutralVariant99,
        bottomBarSurface = tonalPalette.neutralVariant95,
        onBottomBarSurface = tonalPalette.neutralVariant30,
        text = tonalPalette.neutralVariant10,
        textSecondary = tonalPalette.neutralVariant40,
        textDisabled = tonalPalette.neutralVariant100,
        background = tonalPalette.neutralVariant99,
        chip = tonalPalette.neutralVariant95,
        onChip = tonalPalette.neutralVariant40,
        unselected = tonalPalette.neutralVariant60,
        card = tonalPalette.neutralVariant99,
        floorCard = tonalPalette.neutralVariant95,
        divider = tonalPalette.neutralVariant95,
        shadow = tonalPalette.neutralVariant90,
        indicator = tonalPalette.neutralVariant95,
        windowBackground = tonalPalette.neutralVariant99,
        placeholder = tonalPalette.neutralVariant100,
    )
}

@Composable
private fun getDarkDynamicColor(tonalPalette: TonalPalette): ExtendedColors {
    return ExtendedColors(
        theme = "dynamic",
        isNightMode = true,
        primary = tonalPalette.primary80,
        textOnPrimary = tonalPalette.primary10,
        accent = tonalPalette.secondary80,
        onAccent = tonalPalette.secondary20,
        topBar = getDynamicTopBarColor(tonalPalette, true),
        onTopBar = getDynamicOnTopBarColor(tonalPalette, true),
        onTopBarSecondary = getDynamicOnTopBarSecondaryColor(tonalPalette, true),
        onTopBarActive = getDynamicOnTopBarActiveColor(tonalPalette, true),
        topBarSurface = getDynamicTopBarSurfaceColor(tonalPalette, true),
        onTopBarSurface = getDynamicOnTopBarSurfaceColor(tonalPalette, true),
        bottomBarSurface = tonalPalette.neutralVariant20,
        onBottomBarSurface = tonalPalette.neutralVariant70,
        text = tonalPalette.neutralVariant90,
        textSecondary = tonalPalette.neutralVariant70,
        textDisabled = tonalPalette.neutralVariant50,
        background = tonalPalette.neutralVariant10,
        chip = tonalPalette.neutralVariant20,
        onChip = tonalPalette.neutralVariant60,
        unselected = tonalPalette.neutralVariant40,
        card = tonalPalette.neutralVariant20,
        floorCard = tonalPalette.neutralVariant20,
        divider = tonalPalette.neutralVariant20,
        shadow = tonalPalette.neutralVariant20,
        indicator = tonalPalette.neutralVariant10,
        windowBackground = tonalPalette.neutralVariant10,
        placeholder = tonalPalette.neutralVariant50,
    )
}

@Composable
private fun getThemeColorForTheme(theme: String): ExtendedColors {
    val context = LocalContext.current
    val nowTheme = ThemeUtil.getThemeTranslucent(theme)
    val textColor =
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorText, nowTheme))
    val bottomBarColor =
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorNavBar, nowTheme))
    return ExtendedColors(
        nowTheme,
        ThemeUtil.isNightMode(nowTheme),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorNewPrimary, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorAccent, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorOnAccent, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorToolbar, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorToolbarItem, nowTheme)),
        Color(
            App.ThemeDelegate.getColorByAttr(
                context,
                R.attr.colorToolbarItemSecondary,
                nowTheme
            )
        ),
        Color(
            App.ThemeDelegate.getColorByAttr(
                context,
                R.attr.colorToolbarItemActive,
                nowTheme
            )
        ),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorToolbarSurface, nowTheme)),
        Color(
            App.ThemeDelegate.getColorByAttr(
                context,
                R.attr.colorOnToolbarSurface,
                nowTheme
            )
        ),
        bottomBarColor,
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorNavBarSurface, nowTheme)),
        Color(
            App.ThemeDelegate.getColorByAttr(
                context,
                R.attr.colorOnNavBarSurface,
                nowTheme
            )
        ),
        textColor.copy(alpha = ContentAlpha.high),
        textColor.copy(alpha = ContentAlpha.medium),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorOnAccent, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.color_text_disabled, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorBackground, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorChip, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorOnChip, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorUnselected, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorCard, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorFloorCard, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorDivider, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.shadow_color, nowTheme)),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorIndicator, nowTheme)),
        Color(
            App.ThemeDelegate.getColorByAttr(
                context,
                R.attr.colorWindowBackground,
                nowTheme
            )
        ),
        Color(App.ThemeDelegate.getColorByAttr(context, R.attr.colorPlaceholder, nowTheme)),
    )
}

@Composable
fun TiebaLiteTheme(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val theme by remember { ThemeUtil.themeState }
    val isDynamicTheme by rememberPreferenceAsState(
        key = booleanPreferencesKey("useDynamicColorTheme"),
        defaultValue = false
    )
    val isDarkColorPalette by remember {
        derivedStateOf {
            ThemeUtil.isNightMode(theme)
                    || (ThemeUtil.isTranslucentTheme(theme) && theme.contains("light"))
        }
    }

    val extendedColors = if (isDynamicTheme && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getDynamicColor(isDarkColorPalette, dynamicTonalPalette(context))
    } else {
        getThemeColorForTheme(theme)
    }

    val colors = getColorPalette(isDarkColorPalette, extendedColors)

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
