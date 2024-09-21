package com.mhss.app.shifak.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.drug.DrugType
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme

@Composable
fun DrugSmallCard(
    drug: Drug,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.widthIn(175.dp),
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
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
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    ShifakTheme {
        DrugSmallCard(
            drug = Drug(
                id = 1,
                name = "Panadol Extra",
                price = 35.0,
                description = "This is a description",
                isAvailable = true,
                qty = 3,
                productionDate = 0,
                expiryDate = 0,
                drugType = DrugType(id = 0, name = "g", unit = ""),
                pharmacies = listOf(),
                user = null,
                isValid = false,
                isDonated = false,
                updatedAt = "",
                image = null,
                components = listOf(),
            ),
            onClick = {},
            modifier = Modifier.width(200.dp)
        )
    }
}