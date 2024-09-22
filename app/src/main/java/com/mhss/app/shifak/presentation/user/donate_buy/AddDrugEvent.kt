package com.mhss.app.shifak.presentation.user.donate_buy


sealed class AddDrugEvent {
    data class AddDrug(val drugData: AddDrugData) : AddDrugEvent()
    data object NavigateUp : AddDrugEvent()
}