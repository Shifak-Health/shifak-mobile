package com.mhss.app.shifak.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.presentation.ui.theme.PrimaryColor
import com.mhss.app.shifak.presentation.ui.theme.SecondaryColor

@Composable
fun DrugCard(
    drug: Drug,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.widthIn(175.dp),
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
        shape = RoundedCornerShape(24.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column {
            Box(Modifier.fillMaxWidth().aspectRatio(1.4f)) {
                val randomColor = remember {
                    Color(
                        red = (0..200).random(),
                        green = (0..200).random(),
                        blue = (0..200).random()
                    )
                }
                SubcomposeAsyncImage(
                    model = drug.image,
                    contentDescription = drug.name,
                    loading = {
                        Icon(
                            painter = painterResource(R.drawable.ic_drug),
                            contentDescription = null,
                            modifier = Modifier
                                .background(randomColor)
                                .padding(64.dp),
                            tint = Color.White
                        )
                    },
                    error = {
                        Icon(
                            painter = painterResource(R.drawable.ic_drug),
                            contentDescription = null,
                            modifier = Modifier
                                .background(randomColor)
                                .padding(64.dp),
                            tint = Color.White
                        )
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(Modifier.fillMaxWidth().padding(14.dp)) {
                Text(
                    text = drug.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.qty, drug.qty),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = if (drug.isDonated) stringResource(R.string.free)
                    else stringResource(R.string.price_egp, drug.price.toInt()),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(8.dp))

                Button(
                    modifier = modifier
                        .align(Alignment.End)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(PrimaryColor, SecondaryColor),
                            ),
                            shape = ButtonDefaults.shape
                        ),
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = stringResource(R.string.order),
                        style = MaterialTheme
                            .typography
                            .bodyLarge
                            .copy(fontWeight = FontWeight.SemiBold),
                    )
                }
            }

        }
    }
}