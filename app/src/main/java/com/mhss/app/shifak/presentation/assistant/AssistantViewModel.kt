package com.mhss.app.shifak.presentation.assistant

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.NetworkResult
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.assistant.SendAiMessageUseCase
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
@KoinViewModel
class AssistantViewModel(
    private val sendAiMessage: SendAiMessageUseCase,
    private val getEncryptedPreference: GetEncryptedPreferenceUseCase
) : ViewModel() {

    val messages = mutableStateListOf<AiMessage>()
    private val _uiState = MutableStateFlow(AssistantUiState())
    val uiState = _uiState.asStateFlow()

    private var token = ""
    init {
        viewModelScope.launch {
            token = getEncryptedPreference(stringPreferencesKey(TOKEN_KEY), "")
        }
    }

    fun onEvent(event: AssistantEvent) {
        when (event) {
            is AssistantEvent.SendMessage -> viewModelScope.launch {
                val message = event.message
                messages.add(0, message)

                _uiState.update {
                  it.copy(
                      messages = messages,
                      loading = true,
                      error = null
                  )
                }
                when (val result = sendAiMessage(messages.reversed(), token)) {
                    is NetworkResult.Success -> {
                        messages.add(0, result.data)

                        _uiState.update {
                            it.copy(messages = messages, loading = false)
                        }
                    }

                    is NetworkResult.Failure -> {
                        messages.removeAt(0)
                        delay(300)

                        _uiState.update {
                            it.copy(
                                messages = messages,
                                loading = false,
                                error = result
                            )
                        }
                    }
                }
            }

        }
    }
}