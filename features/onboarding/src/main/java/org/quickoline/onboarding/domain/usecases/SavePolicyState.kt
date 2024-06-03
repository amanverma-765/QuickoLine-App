package org.quickoline.onboarding.domain.usecases

import org.quickoline.onboarding.domain.repository.OnBoardingRepository

internal class SavePolicyState(
    private val onBoardingRepository: OnBoardingRepository
) {
    suspend operator fun invoke(state: Boolean) {
        onBoardingRepository.savePolicyState(state)
    }
}