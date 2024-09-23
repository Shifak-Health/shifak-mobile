package com.mhss.app.shifak.presentation.user.donate_buy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.data.remote.user.UserApi
import com.mhss.app.shifak.data.remote.user.model.AddDrugRequestBody
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import com.mhss.app.shifak.util.formattedForNetwork
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddDrugViewModel(
    private val fileManager: FileManager,
    private val getEncryptedPreference: GetEncryptedPreferenceUseCase,
    private val api: UserApi
) : ViewModel() {

    var state by mutableStateOf(AddDrugUiState())
        private set

    fun onEvent(event: AddDrugEvent) {
        when (event) {
            is AddDrugEvent.AddDrug -> viewModelScope.launch {
                try {
                    state = state.copy(loading = true, error = null)
                    val token = getEncryptedPreference(stringPreferencesKey(TOKEN_KEY), "")
                    val dta = event.drugData
                    val response = api.addDrug(
                        token,
                        AddDrugRequestBody(
                            name = dta.name,
                            drugTypeId = dta.drugTypeId,
                            pharmacyBranchId = if (dta.isDonated) 2 else null,
                            price = dta.price,
                            qty = dta.qty,
                            productionDate = dta.productionDate.formattedForNetwork(),
                            expiryDate = dta.expiryDate.formattedForNetwork(),
                            isDonated = if (dta.isDonated) 1 else 0,
                            isExpired = if (dta.isExpired) 1 else 0,
                            images = dta.images?.map { fileManager.uriToByteArray(it) }
                        )
                    )
                    state = if (response.message == "تم إضافة الدواء بنجاح") {
                        state.copy(
                            loading = false,
                            done = true,
                            error = null
                        )
                    } else state.copy(
                        loading = false,
                        error = response.message
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    state = state.copy(
                        loading = false,
                        error = e.localizedMessage
                    )
                }
            }
            else -> Unit
        }
    }

}