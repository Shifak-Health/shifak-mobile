package com.mhss.app.shifak.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme
import com.mhss.app.shifak.util.Gender

@Composable
fun GenderPicker(
    genders: Array<Gender>,
    selectedGender: Gender,
    onGenderSelected: (Gender) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = modifier
            .clickable {
                expanded = true
            }
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        DropdownMenu(
            expanded,
            onDismissRequest = { expanded = false },

            ) {
            genders.forEach { gender ->
                DropdownMenuItem(
                    text = { Text(stringResource(gender.stringRes)) },
                    onClick = {
                        onGenderSelected(gender)
                        expanded = false
                    }
                )
            }
        }
        Text(
            stringResource(selectedGender.stringRes),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }

}

@Preview
@Composable
private fun GenderPickerPreview() {
    ShifakTheme {
        GenderPicker(
            genders = Gender.entries.toTypedArray(),
            selectedGender = Gender.MALE,
            onGenderSelected = {},
            modifier = Modifier
        )
    }
}