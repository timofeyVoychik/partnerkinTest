package ru.timofey.feature_conference.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.timofey.core_ui.theme.Inter

@Composable
fun ConferenceAboutSection(about: String) {
    Column(
        modifier = Modifier
            .width(328.dp)
            .padding(top = 32.dp)
    ) {
        Text(
            text = "О событии",
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = (18 * 1.4).sp,
            color = Color(0xFF0E1234)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = about,
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = (15 * 1.6).sp,
            color = Color(0xFF0E1234)
        )
    }
}