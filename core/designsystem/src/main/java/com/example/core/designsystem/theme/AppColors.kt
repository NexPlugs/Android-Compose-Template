package com.example.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


/**
 * Color palette for the application.
 */
@Immutable
data class AppColors(
    val primary: Color,
    val background: Color,
    val backgroundLight: Color,
    val backgroundDark: Color,
    val absoluteWhite: Color,
    val absoluteBlack: Color,
    val white: Color,
    val white12: Color,
    val white56: Color,
    val white70: Color,
    val black: Color,
    val gray21: Color,
    val bug: Color,
    val dark: Color,
    val dragon: Color,
    val electric: Color,
    val fairy: Color,
    val fire: Color,
    val fighting: Color,
    val flying: Color,
    val ghost: Color,
    val steel: Color,
    val ice: Color,
    val poison: Color,
    val psychic: Color,
    val rock: Color,
    val water: Color,
    val grass: Color,
    val ground: Color,
    val orange: Color,
    val green: Color,
    val blue: Color,
) {
    companion object {

        @Composable
        fun defaultDarkMode(): AppColors = AppColors(
            primary = Color(0xFF0078D4),
            background = Color(0xFF121212),
            backgroundLight = Color(0xFF1E1E1E),
            backgroundDark = Color(0xFF000000),
            absoluteWhite = Color(0xFFFFFFFF),
            absoluteBlack = Color(0xFF000000),
            white = Color(0xFFF5F5F5),
            white12 = Color(0x1FFFFFFF),
            white56 = Color(0x8FFFFFFF),
            white70 = Color(0xB3FFFFFF),
            black = Color(0xFF212121),
            gray21 = Color(0xFF363636),
            bug = Color(0xFFA8B820),
            dark = Color(0xFF705848),
            dragon = Color(0xFF7038F8),
            electric = Color(0xFFF8D030),
            fairy = Color(0xFFEE99AC),
            fire = Color(0xFFF08030),
            fighting = Color(0xFFC03028),
            flying = Color(0xFFA890F0),
            ghost = Color(0xFF705898),
            steel = Color(0xFFB8B8D0),
            ice = Color(0xFF98D8D8),
            poison = Color(0xFFA040A0),
            psychic = Color(0xFFF85888),
            rock = Color(0xFFB8A038),
            water = Color(0xFF6890F0),
            grass = Color(0xFF78C850),
            ground = Color(0xFFE0C068),
            orange = Color(0xFFFFA500),
            green = Color(0xFF00FF00),
            blue = Color(0xFF0000FF)
        )


        @Composable
        fun defaultLightMode(): AppColors = AppColors(
            primary = Color(0xFF0078D4),
            background = Color(0xFFFFFFFF),
            backgroundLight = Color(0xFFF5F5F5),
            backgroundDark = Color(0xFFE0E0E0),
            absoluteWhite = Color(0xFFFFFFFF),
            absoluteBlack = Color(0xFF000000),
            white = Color(0xFF212121),
            white12 = Color(0x1F000000),
            white56 = Color(0x8F000000),
            white70 = Color(0xB3000000),
            black = Color(0xFF212121),
            gray21 = Color(0xFFBDBDBD),
            bug = Color(0xFFA8B820),
            dark = Color(0xFF705848),
            dragon = Color(0xFF7038F8),
            electric = Color(0xFFF8D030),
            fairy = Color(0xFFEE99AC),
            fire = Color(0xFFF08030),
            fighting = Color(0xFFC03028),
            flying = Color(0xFFA890F0),
            ghost = Color(0xFF705898),
            steel = Color(0xFFB8B8D0),
            ice = Color(0xFF98D8D8),
            poison = Color(0xFFA040A0),
            psychic = Color(0xFFF85888),
            rock = Color(0xFFB8A038),
            water = Color(0xFF6890F0),
            grass = Color(0xFF78C850),
            ground = Color(0xFFE0C068),
            orange = Color(0xFFFFA500),
            green = Color(0xFF00FF00),
            blue = Color(0xFF0000FF)
        )
    }
}