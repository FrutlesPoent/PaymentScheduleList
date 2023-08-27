package ru.paymentscheduler.domain.repository

import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo

interface PaymentSchedulerRepository {

	suspend fun getScheduleByMonth(month: Month): List<PaymentInfo>
}