package ru.paymentscheduler.domain.usecase

import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo
import ru.paymentscheduler.domain.repository.PaymentSchedulerRepository

class GetPaymentSchedulerByMonthUseCase(
	private val repository: PaymentSchedulerRepository,
) {

	suspend operator fun invoke(month: Month): List<PaymentInfo> {
		return repository.getScheduleByMonth(month)
	}
}