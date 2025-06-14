package com.arrazyfathan.tudu.core.ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.cyrene
import tudu.composeapp.generated.resources.inter_black
import tudu.composeapp.generated.resources.inter_bold
import tudu.composeapp.generated.resources.inter_extrabold
import tudu.composeapp.generated.resources.inter_extralight
import tudu.composeapp.generated.resources.inter_light
import tudu.composeapp.generated.resources.inter_medium
import tudu.composeapp.generated.resources.inter_regular
import tudu.composeapp.generated.resources.inter_semibold
import tudu.composeapp.generated.resources.inter_thin

@Composable
fun InterFontFamily() =
    FontFamily(
        Font(Res.font.inter_thin, weight = FontWeight.Thin),
        Font(Res.font.inter_extralight, weight = FontWeight.ExtraLight),
        Font(Res.font.inter_light, weight = FontWeight.Light),
        Font(Res.font.inter_regular, weight = FontWeight.Normal),
        Font(Res.font.inter_medium, weight = FontWeight.Medium),
        Font(Res.font.inter_semibold, weight = FontWeight.SemiBold),
        Font(Res.font.inter_bold, weight = FontWeight.Bold),
        Font(Res.font.inter_black, weight = FontWeight.Black),
        Font(Res.font.inter_extrabold, weight = FontWeight.ExtraBold),
    )

@Composable
fun CyreneFontFamily() =
    FontFamily(
        Font(Res.font.cyrene, weight = FontWeight.Normal),
        Font(Res.font.cyrene, weight = FontWeight.Bold),
    )

@Composable
fun Typography() =
    Typography().run {
        val fontFamily = InterFontFamily()

        copy(
            displayLarge = displayLarge.copy(fontFamily = fontFamily),
            displayMedium = displayMedium.copy(fontFamily = fontFamily),
            displaySmall = displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
            titleLarge = titleLarge.copy(fontFamily = fontFamily),
            titleMedium = titleMedium.copy(fontFamily = fontFamily),
            titleSmall = titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = bodySmall.copy(fontFamily = fontFamily),
            labelLarge = labelLarge.copy(fontFamily = fontFamily),
            labelMedium = labelMedium.copy(fontFamily = fontFamily),
            labelSmall = labelSmall.copy(fontFamily = fontFamily),
        )
    }
