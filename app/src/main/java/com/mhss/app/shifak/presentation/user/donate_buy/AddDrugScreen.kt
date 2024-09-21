package com.mhss.app.shifak.presentation.user.donate_buy

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme

@Composable
fun AddDrugScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .statusBarsPadding()
    ) {
        var name by rememberSaveable { mutableStateOf("") }
        var productionDate by rememberSaveable { mutableStateOf("") }
        var expiryDate by rememberSaveable { mutableStateOf("") }
        var location by rememberSaveable { mutableStateOf("") }
        var qty by rememberSaveable { mutableStateOf("") }
        var type by rememberSaveable { mutableStateOf("") }
        var price by rememberSaveable { mutableStateOf("") }
        var selectedImages by rememberSaveable { mutableStateOf(emptyList<Uri>()) }
        val photoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = { uris -> selectedImages = uris }
        )

        MainTopAppBar(
            title = stringResource(R.string.add_drug_details),
            onNavigateUp = onNavigateUp
        )

        Spacer(Modifier.height(24.dp))

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
            Spacer(Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                MainTextField(
                    value = productionDate,
                    onValueChange = { productionDate = it },
                    hint = stringResource(R.string.production_date),
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number
                )
                MainTextField(
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    hint = stringResource(R.string.expiry_date),
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number
                )
            }
            Spacer(Modifier.height(12.dp))

            MainTextField(
                value = location,
                onValueChange = { location = it },
                hint = stringResource(R.string.location),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                MainTextField(
                    value = type,
                    onValueChange = { type = it },
                    hint = stringResource(R.string.drug_type),
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Text
                )
                MainTextField(
                    value = qty,
                    onValueChange = { qty = it },
                    hint = stringResource(R.string.drug_quantity),
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number
                )
            }
            Spacer(Modifier.height(12.dp))

            MainTextField(
                value = price,
                onValueChange = { price = it },
                hint = stringResource(R.string.price),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number
            )
        }

        MainButton(
            stringResource(R.string.confirm),
            onClick = {

            },
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
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
                {}
            )
        }
    }
}