package com.arrazyfathan.tudu.features.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.EditorialOldFontFamily
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import com.arrazyfathan.tudu.features.home.domain.model.DummyJournal
import com.arrazyfathan.tudu.features.home.domain.model.dummyJournal
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun JournalItem(
    journal: DummyJournal,
    onClick: (DummyJournal) -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = AppColors.White,
                contentColor = AppColors.Black,
            ),
        border =
            BorderStroke(
                color = AppColors.Black.copy(alpha = 0.1f),
                width = 0.5.dp,
            ),
        onClick = {
            onClick(journal)
        },
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = journal.title,
                fontFamily = EditorialOldFontFamily(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            VerticalSpacer(10.dp)
            Text(
                text = journal.content,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        color = AppColors.Black.copy(alpha = 0.5f),
                        fontSize = 14.sp,
                    ),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )

            VerticalSpacer(20.dp)

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                items(journal.tags) { tag ->
                    TagItem(tag = tag)
                }
            }
        }
    }
}

@Preview
@Composable
fun JournalItemPreview() {
    JournalItem(
        journal = dummyJournal[0],
        onClick = {},
    )
}
