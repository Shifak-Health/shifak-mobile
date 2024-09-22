package com.mhss.app.shifak.presentation.user.donate_buy

sealed class GetMedicationsEvent {
    data object NavigateUp : GetMedicationsEvent()
}