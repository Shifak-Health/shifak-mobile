package com.mhss.app.shifak.presentation.user.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.drug.DrugType
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserHomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        UserHomeUiState()
    )
    val uiState = _uiState.asStateFlow()
}