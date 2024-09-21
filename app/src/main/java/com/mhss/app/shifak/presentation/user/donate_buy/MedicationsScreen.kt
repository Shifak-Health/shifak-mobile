package com.mhss.app.shifak.presentation.user.donate_buy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.drug.DrugType
import com.mhss.app.shifak.presentation.common.DrugCard
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme

@Composable
fun MedicationsScreen(
    medications: List<Drug>,
    onNavigateUp: () -> Unit
) {
    Column(Modifier.fillMaxSize().navigationBarsPadding()) {
        MainTopAppBar(
            title = stringResource(R.string.shop_used_medications),
            onNavigateUp = onNavigateUp
        )
        Spacer(Modifier.height(12.dp))
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(medications) { drug ->
                DrugCard(
                    drug = drug,
                    onClick = {}
                )
            }
            item {
                Spacer(Modifier.padding(WindowInsets.navigationBars.asPaddingValues()))
            }
        }
    }
}

@Preview(device = Devices.PIXEL_7_PRO, locale = "ar")
@Composable
private fun LoginScreenPreview() {
    ShifakTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MedicationsScreen(
                listOf(
                    Drug(
                        id = 1,
                        name = "بانادول اكسترا",
                        description = "",
                        price = 43.0,
                        qty = 2,
                        productionDate = 0,
                        expiryDate = 0,
                        drugType = DrugType(
                            id = 8181,
                            name = "gm",
                            unit = "gm"
                        ),
                        pharmacies = listOf(),
                        user = null,
                        isValid = false,
                        isAvailable = false,
                        isDonated = false,
                        updatedAt = "doming",
                        image = "",
                        components = listOf()
                    ),
                    Drug(
                        id = 2,
                        name = "Panadol cold & flu",
                        description = "",
                        price = 40.0,
                        qty = 2,
                        productionDate = 0,
                        expiryDate = 0,
                        drugType = DrugType(
                            id = 8181,
                            name = "gm",
                            unit = "gm"
                        ),
                        pharmacies = listOf(),
                        user = null,
                        isValid = false,
                        isAvailable = false,
                        isDonated = true,
                        updatedAt = "doming",
                        image = null,
                        components = listOf()
                    ),
                    Drug(
                        id = 3,
                        name = "Panadol",
                        description = "",
                        price = 40.0,
                        qty = 2,
                        productionDate = 0,
                        expiryDate = 0,
                        drugType = DrugType(
                            id = 8181,
                            name = "gm",
                            unit = "gm"
                        ),
                        pharmacies = listOf(),
                        user = null,
                        isValid = false,
                        isAvailable = false,
                        isDonated = true,
                        updatedAt = "doming",
                        image = null,
                        components = listOf()

                    )
                ),
                {}
            )
        }
    }
}