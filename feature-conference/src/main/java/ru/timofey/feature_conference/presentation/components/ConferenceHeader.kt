package ru.timofey.feature_conference.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import ru.timofey.core_ui.model.UiConference
import ru.timofey.core_ui.theme.Inter

@Composable
fun ConferenceHeader(conference: UiConference) {
    Column(
        modifier = Modifier
            .width(328.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Конференция",
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = (14 * 1.6).sp,
                color = Color(0xFF0E1234)
            )
            Text(
                text = conference.name,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = (24 * 1.2).sp,
                color = Color(0xFF0E1234),
                maxLines = 2
            )
        }

        Image(
            painter = rememberAsyncImagePainter(conference.imageUrl),
            contentDescription = conference.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(328.dp)
                .height(219.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            conference.categories.forEach { category ->
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .wrapContentWidth()
                        .background(Color(0xFFEFF2F9), RoundedCornerShape(60.dp))
                        .padding(horizontal = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.name,
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        lineHeight = (11 * 1.4).sp,
                        color = Color(0xFF0E1234),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}