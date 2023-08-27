package ru.paymentscheduler.data.repository

import ru.paymentscheduler.data.dao.PaymentScheduleDao
import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo
import ru.paymentscheduler.domain.repository.PaymentSchedulerRepository

class RepositoryImpl(
	private val dao: PaymentScheduleDao,
) : PaymentSchedulerRepository {

	override suspend fun getScheduleByMonth(month: Month): List<PaymentInfo> {
		return dao.getPaymentInfo(month = month)
	}
}