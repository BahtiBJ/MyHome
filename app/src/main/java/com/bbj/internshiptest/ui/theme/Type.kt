package com.bbj.internshiptest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bbj.internshiptest.R

private val appFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.roboto_bold,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_regular,
            weight = FontWeight.W500,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_thin,
            weight = FontWeight.W300,
            style = FontStyle.Normal
        )
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = appFontFamily,
        color = Color.Black,
        fontWeight = FontWeight.W500,
        fontSize = 21.sp
    ),
    titleMedium = TextStyle(
        fontFamily = appFontFamily,
        color = Color.Black,
        fontWeight = FontWeight.W500,
        fontSize = 17.sp
    ),
    titleSmall = TextStyle(
        fontFamily = appFontFamily,
        color = Color.Black,
        fontWeight = FontWeight.W300,
        fontSize = 17.sp
    ),
    labelMedium = TextStyle(
        fontFamily = appFontFamily,
        color = Color.Black,
        fontWeight = FontWeight.W500,
        fontSize = 17.sp
    ),
    labelSmall = TextStyle(
        fontFamily = appFontFamily,
        color = Gray99,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp
    ),
    displayMedium = TextStyle(
        fontFamily = appFontFamily,
        color = BlueF4,
        fontWeight = FontWeight.W500,
        fontSize = 21.sp
    )

)