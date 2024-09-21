package com.mhss.app.shifak.presentation.user.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.drug.DrugType
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme

@Composable
fun UserHomeScreen(
    state: UserHomeUiState,
    onNavigate: (Screen) -> Unit,
    onEvent: (UserHomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreenAppBar(
            location = state.location,
            hasNotifications = state.hasNotifications,
            onLocationClick = {
                // TODO
            },
            onNotificationsClick = {
                // TODO
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            UserActionCard(
                icon = painterResource(R.drawable.ic_donate),
                title = stringResource(R.string.donate_sell),
                onClick = { onNavigate(Screen.AddMedicationScreen) },
                modifier = Modifier
                    .weight(0.8f)
                    .padding(16.dp)
            )
            UserActionCard(
                icon = painterResource(R.drawable.ic_buy),
                title = stringResource(R.string.shop),
                onClick = { onNavigate(Screen.MedicationsScreen) },
                modifier = Modifier
                    .weight(0.8f)
                    .padding(20.dp)
            )
        }
        Spacer(Modifier.height(18.dp))
        SmartAssistantCard(
            onTryClick = {
                onNavigate(Screen.SmartAssistantScreen)
            }
        )
        Spacer(Modifier.height(18.dp))

        ExploreDrugsSection(
            drugs = state.exploreMedications,
            onDrugClick = { drug ->
                // TODO
            },
            onShowAllClick = {
                onNavigate(Screen.MedicationsScreen)
            }
        )

        Spacer(Modifier.height(18.dp))

        NearbyPharmaciesSection(
            pharmacies = state.pharmaciesNearby,
            onPharmacyClick = { pharmacy ->
                // TODO
            },
            onShowAllClick = {
                // TODO
            }
        )

        Spacer(Modifier.height(48.dp))

    }
}

@Composable
private fun HomeScreenAppBar(
    location: String,
    hasNotifications: Boolean,
    onLocationClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onLocationClick() }
        ) {
            Text(
                text = location,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }
        BadgedBox(
            badge = {
                if (hasNotifications) Badge()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_notifications),
                contentDescription = stringResource(R.string.notifications),
                modifier = Modifier.clickable { onNotificationsClick() }
            )
        }
    }
}

@Composable
fun UserActionCard(
    icon: Painter,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            6.dp
        ),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .aspectRatio(1f)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
                    .copy(
                        fontWeight = FontWeight.Bold
                    ),
                modifier = Modifier.padding(top = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )

        }
    }
}

@Composable
private fun SmartAssistantCard(
    onTryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .clickable { onTryClick() }
            .background(Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            ))
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_chat_bot),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.width(8.dp))
            Text(
                stringResource(R.string.talk_to_our_assistant),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Button(
            onClick = onTryClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.primary
            ),
        ) {
            Text(
                text = stringResource(R.string.try_now),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
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
            UserHomeScreen(
                state = UserHomeUiState(
                    location = "الدقهلية",
                    exploreMedications = listOf(
                        Drug(
                            id = 1,
                            name = "Panadol Extra",
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
                            image = null,
                            components = listOf()
                        ),
                        Drug(
                            id = 2,
                            name = "Panadol Cold & Flu",
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
                    pharmaciesNearby = listOf(
                        Pharmacy(
                            id = 1,
                            name = "صيدلية الدواء",
                            hotline = "",
                            order = 1,
                            isActive = true,
                            logoUrl = "https://www.al-dawaa.com/media/media/logo/stores/2/logo_720_.png"

                        )
                    ),
                    hasNotifications = true
                ),
                onNavigate = {},
                onEvent = {}
            )
        }
    }
}