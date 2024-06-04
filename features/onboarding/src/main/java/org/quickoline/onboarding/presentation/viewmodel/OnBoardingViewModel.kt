package org.quickoline.onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.quickoline.onboarding.domain.usecases.OnBoardingUseCases

internal class OnBoardingViewModel(
    private val onBoardingUseCases: OnBoardingUseCases
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow(OnBoardingUiStates())
    val onBoardingState = _onBoardingState.asStateFlow()

    fun onEvent(event: OnBoardingUiEvents) {
        when (event) {
            is OnBoardingUiEvents.SaveUserEntryState -> saveUserEntryState()
            is OnBoardingUiEvents.SavePolicyState -> savePolicyState(event.isAccepted)
        }
    }

    private fun saveUserEntryState() {
        viewModelScope.launch {
            try {
                onBoardingUseCases.saveUserEntryState()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun savePolicyState(isAccepted: Boolean) {
        viewModelScope.launch {
            try {
                onBoardingUseCases.savePolicyState(isAccepted)
                _onBoardingState.update { state ->
                    state.copy(isPolicyAccepted = isAccepted)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}