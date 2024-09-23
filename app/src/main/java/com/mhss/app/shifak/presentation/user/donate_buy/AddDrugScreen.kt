package com.mhss.app.shifak.presentation.user.donate_buy

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.DatePickerField
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme
import kotlin.time.Duration.Companion.hours

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDrugScreen(
    state: AddDrugUiState,
    onEvent: (AddDrugEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .statusBarsPadding()
    ) {
        var name by rememberSaveable { mutableStateOf("") }
        val productionDateState = rememberDatePickerState()
        val expiryDateState = rememberDatePickerState()
//        var location by rememberSaveable { mutableStateOf("") }
        var qty by rememberSaveable { mutableStateOf("") }
        var type by rememberSaveable { mutableStateOf("قرص") }
        var price by rememberSaveable { mutableStateOf("") }
        val selectedImages = remember { mutableStateListOf<Uri>() }
        val photoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = { uris ->
                selectedImages.clear()
                selectedImages.addAll(uris)
            }
        )
        var donated by rememberSaveable { mutableStateOf(false) }
        var expired by rememberSaveable { mutableStateOf(false) }
        val context = LocalContext.current

        LaunchedEffect(state) {
            if (state.done) onEvent(AddDrugEvent.NavigateUp)
            if (state.error != null) {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            }
        }

        MainTopAppBar(
            title = stringResource(R.string.add_drug_details),
            onNavigateUp = {
                onEvent(AddDrugEvent.NavigateUp)
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            MainTextField(
                value = name,
                onValueChange = { name = it },
                hint = stringResource(R.string.drug_name)
            )
            Spacer(Modifier.height(24.dp))
            AddDrugPhotosCard(
                imageUris = selectedImages,
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )
            Spacer(Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = expired,
                    onClick = { expired = !expired },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    stringResource(R.string.expired),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            AnimatedVisibility(!expired, modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DatePickerField(
                        state = productionDateState,
                        hint = stringResource(R.string.production_date),
                        modifier = Modifier.weight(1f)
                    )
                    DatePickerField(
                        state = expiryDateState,
                        hint = stringResource(R.string.expiry_date),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

//            MainTextField(
//                value = location,
//                onValueChange = { location = it },
//                hint = stringResource(R.string.location),
//                modifier = Modifier.fillMaxWidth()
//            )

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                MainTextField(
                    value = qty,
                    onValueChange = { qty = it },
                    hint = stringResource(R.string.drug_quantity),
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number
                )
                MainTextField(
                    value = type,
                    onValueChange = { type = it },
                    hint = stringResource(R.string.drug_type),
                    modifier = Modifier.weight(.5f),
                    keyboardType = KeyboardType.Text
                )
            }
            Spacer(Modifier.height(12.dp))
            AnimatedVisibility(!expired) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = donated,
                        onClick = { donated = !donated },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        stringResource(R.string.donation),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(8.dp))
                    MainTextField(
                        value = if (donated) "0" else price,
                        onValueChange = { price = it },
                        hint = stringResource(R.string.price),
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !donated,
                        keyboardType = KeyboardType.Number
                    )
                }
            }
        }

        MainButton(
            stringResource(R.string.confirm),
            onClick = {
                onEvent(
                    AddDrugEvent.AddDrug(
                        AddDrugData(
                            name = name,
                            price = price.toDoubleOrNull() ?: 1.0,
                            qty = qty.toIntOrNull() ?: 1,
                            productionDate = productionDateState.selectedDateMillis ?: 0L,
                            expiryDate = if (expired) 30.hours.inWholeMilliseconds else expiryDateState.selectedDateMillis ?: 0L,
                            isDonated = donated,
                            isExpired = expired,
                            images = selectedImages
                        )
                    )
                )
            },
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            loading = state.loading
        )
    }
}

@Preview(device = Devices.PIXEL_7_PRO, locale = "ar")
@Composable
private fun LoginScreenPreview() {
    ShifakTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AddDrugScreen(
                AddDrugUiState(),
                {}
            )
        }
    }
}