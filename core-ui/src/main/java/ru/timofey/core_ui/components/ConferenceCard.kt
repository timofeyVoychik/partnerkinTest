package ru.timofey.core_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import ru.timofey.core_ui.R
import ru.timofey.core_ui.model.UiConference
import ru.timofey.core_ui.theme.Inter

@Composable
fun ConferenceCard(
    conference: UiConference,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (conference.isCancelled) {
        Color(0x1AFF3333)
    } else {
        Color(0xFFEFF2F9)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(conference.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            if (conference.isCancelled) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .wrapContentWidth()
                        .border(
                            width = 1.dp,
                            color = Color(0xFFFF3333),
                            shape = RoundedCornerShape(60.dp)
                        )
                        .padding(horizontal = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.alert),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Отменено",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            lineHeight = (11 * 1.4).sp, // 140%
                            letterSpacing = 0.sp,
                            color = Color(0xFFFF3333),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Text(
                text = conference.name,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                letterSpacing = 0.sp,
                color = Color(0xFF060A3C),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            DateBlock(
                imageUrl = conference.imageUrl ?: "",
                startDay = conference.startDay,
                endDay = conference.endDay,
                month = conference.month
            )

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
            ) {
                conference.tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp, bottom = 8.dp)
                            .height(24.dp)
                            .wrapContentWidth()
                            .background(Color.White, RoundedCornerShape(60.dp))
                            .padding(horizontal = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tag,
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            lineHeight = (11 * 1.4).sp,
                            color = Color(0xFF060A3C),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.wrapContentSize()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gps),
                    contentDescription = null,
                    tint = Color(0xFF060A3C),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = conference.location,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = (14 * 1.6).sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFF060A3C)
                )
            }
        }
    }
}

@Composable
private fun DateBlock(
    imageUrl: String,
    startDay: String,
    endDay: String,
    month: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF456AEF).copy(alpha = 0.04f))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(156.dp)
                    .height(104.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 12.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateItem(
                    day = startDay,
                    month = month,
                    modifier = Modifier.weight(1f, fill = false)
                )

                Text(
                    text = "–",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Light,
                    fontSize = 32.sp,
                    color = Color(0xFF0E1234),
                    modifier = Modifier.padding(horizontal = 4.dp).alignByBaseline()
                )

                DateItem(
                    day = endDay,
                    month = month,
                    modifier = Modifier.weight(1f, fill = false)
                )
            }
        }
    }
}

@Composable
private fun DateItem(
    day: String,
    month: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .widthIn(max = 60.dp)
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day,
            fontFamily = Inter,
            fontWeight = FontWeight.Light,
            fontSize = 36.sp,
            lineHeight = 42.sp,
            letterSpacing = (-0.6).sp,
            color = Color(0xFF0E1234),
            maxLines = 1,
            overflow = TextOverflow.Visible,
            softWrap = false,
            modifier = Modifier
                .wrapContentSize()
                .scale(
                    scaleX = if (day.length > 2) 0.9f else 1f,
                    scaleY = 1f
                )
        )
        Text(
            text = month.uppercase(),
            fontFamily = Inter,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.sp,
            color = Color(0xFF0E1234).copy(alpha = 0.6f),
            maxLines = 1
        )
    }
}