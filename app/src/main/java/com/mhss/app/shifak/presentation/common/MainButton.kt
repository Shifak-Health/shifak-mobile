package com.mhss.app.shifak.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.presentation.ui.theme.PrimaryColor
import com.mhss.app.shifak.presentation.ui.theme.SecondaryColor
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
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
            text = text,
            style = MaterialTheme
                .typography
                .headlineSmall
                .copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
@Preview(widthDp = 320)
fun MainButtonPreview() {
    ShifakTheme {
        MainButton(
            text = "تسجيل الدخول",
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}