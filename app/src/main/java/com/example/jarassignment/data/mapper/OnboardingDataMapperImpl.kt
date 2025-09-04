package com.example.jarassignment.data.mapper

import com.example.jarassignment.data.api.model.OnboardingResponse
import com.example.jarassignment.data.api.model.OnboardingResponse as ApiResponse
import com.example.jarassignment.domain.model.OnboardingResponse as DomainResponse
import com.example.jarassignment.domain.model.OnboardingData
import com.example.jarassignment.domain.model.ManualBuyEducationData
import com.example.jarassignment.domain.model.EducationCard
import com.example.jarassignment.domain.model.SaveButtonCta
import javax.inject.Inject

class OnboardingDataMapperImpl @Inject constructor() : OnboardingDataMapper {
    
    override fun mapToDomainModel(apiResponse: ApiResponse): DomainResponse {
        return DomainResponse(
            success = apiResponse.success,
            data = mapOnboardingData(apiResponse.data)
        )
    }
    
    private fun mapOnboardingData(apiData: ApiResponse.OnboardingData): OnboardingData {
        return OnboardingData(
            manualBuyEducationData = mapManualBuyEducationData(apiData.manualBuyEducationData)
        )
    }

    private fun mapManualBuyEducationData(apiData: OnboardingResponse.ManualBuyEducationData): ManualBuyEducationData {
        return ManualBuyEducationData(
            toolBarText = apiData.toolBarText,
            introTitle = apiData.introTitle,
            introSubtitle = apiData.introSubtitle,
            educationCardList = apiData.educationCardList.map { mapEducationCard(it) },
            saveButtonCta = mapSaveButtonCta(apiData.saveButtonCta),
            ctaLottie = apiData.ctaLottie,
            screenType = apiData.screenType,
            cohort = apiData.cohort,
            combination = apiData.combination,
            collapseCardTiltInterval = apiData.collapseCardTiltInterval,
            collapseExpandIntroInterval = apiData.collapseExpandIntroInterval,
            bottomToCenterTranslationInterval = apiData.bottomToCenterTranslationInterval,
            expandCardStayInterval = apiData.expandCardStayInterval,
            seenCount = apiData.seenCount,
            actionText = apiData.actionText,
            shouldShowOnLandingPage = apiData.shouldShowOnLandingPage,
            toolBarIcon = apiData.toolBarIcon,
            introSubtitleIcon = apiData.introSubtitleIcon,
            shouldShowBeforeNavigating = apiData.shouldShowBeforeNavigating
        )
    }

    private fun mapEducationCard(apiCard: OnboardingResponse.EducationCard): EducationCard {
        return EducationCard(
            image = apiCard.image,
            collapsedStateText = apiCard.collapsedStateText,
            expandStateText = apiCard.expandStateText,
            backgroundColor = apiCard.backgroundColor,
            strokeStartColor = apiCard.strokeStartColor,
            strokeEndColor = apiCard.strokeEndColor,
            startGradient = apiCard.startGradient,
            endGradient = apiCard.endGradient
        )
    }

    private fun mapSaveButtonCta(apiCta: OnboardingResponse.SaveButtonCta): SaveButtonCta {
        return SaveButtonCta(
            text = apiCta.text,
            deeplink = apiCta.deeplink,
            backgroundColor = apiCta.backgroundColor,
            textColor = apiCta.textColor,
            strokeColor = apiCta.strokeColor,
            icon = apiCta.icon,
            order = apiCta.order
        )
    }
}