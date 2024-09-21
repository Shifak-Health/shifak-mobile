package com.mhss.app.shifak.presentation.user.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy

@Composable
fun PharmacySmallCard(
    pharmacy: Pharmacy,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.widthIn(min = 175.dp),
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = pharmacy.logoUrl,
                contentDescription = pharmacy.name,
                loading = { CircularProgressIndicator() },
                error = {
                    val randomColor = remember {
                        Color(
                            red = (0..200).random(),
                            green = (0..200).random(),
                            blue = (0..200).random()
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.ic_pharmacy),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(randomColor)
                            .padding(12.dp)
                            .size(24.dp),
                        tint = Color.White
                    )
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = pharmacy.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}