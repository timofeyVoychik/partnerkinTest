package ru.timofey.feature_conference.presentation.components

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.timofey.core_ui.R
import ru.timofey.core_ui.model.UiConference
import ru.timofey.core_ui.theme.Inter
import ru.timofey.core_ui.util.formatDateRange
import androidx.core.net.toUri

@Composable
fun ConferenceInfoSection(conference: UiConference) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .width(328.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Дата",
                tint = Color(0xFF456AEF),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = formatDateRange(conference.startDate, conference.endDate),
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = (16 * 1.6).sp,
                color = Color(0xFF060A3C)
            )
        }

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.gps),
                contentDescription = "Место",
                tint = Color(0xFF456AEF),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "${conference.city}, ${conference.country}",
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = (16 * 1.6).sp,
                color = Color(0xFF060A3C)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val url = conference.registerUrl
                val intent = Intent(Intent.ACTION_VIEW, url?.toUri())
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF456AEF)),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            Text(
                text = "Регистрация",
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}