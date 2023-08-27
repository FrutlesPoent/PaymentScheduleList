package ru.paymentscheduler.presentation

import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo

sealed class MainState {

	object Initial : MainState()

	object Loading : MainState()

	data class Content(
		val contentType: ContentType,
	) : MainState()

	object Error : MainState()
}

sealed class ContentType {

	data class Empty(
		val currentMonth: Month,
	) : ContentType()

	data class Content(
		val paymentList: List<PaymentInfo>,
		val currentMonth: Month,
	) : ContentType()
}
