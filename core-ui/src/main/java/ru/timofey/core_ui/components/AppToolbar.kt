package ru.timofey.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.timofey.core_ui.R
import ru.timofey.core_ui.theme.Inter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String? = null,
    onBackClick: () -> Unit,
    onHeadphonesClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            if (!title.isNullOrEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        lineHeight = (18 * 1.4).sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFF0E1234),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth())
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Назад",
                    tint = Color(0xFF1B1B1D),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        actions = {
            if (onHeadphonesClick != null) {
                IconButton(onClick = onHeadphonesClick) {
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.head1),
                            contentDescription = null,
                            tint = Color(0xFF1B1B1D),
                            modifier = Modifier.size(20.dp)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.head2),
                            contentDescription = "Аудио",
                            tint = Color(0xFF1B1B1D),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}