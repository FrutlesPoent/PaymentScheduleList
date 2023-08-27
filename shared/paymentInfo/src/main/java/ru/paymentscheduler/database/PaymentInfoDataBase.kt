package ru.paymentscheduler.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.paymentscheduler.data.dao.PaymentScheduleDao
import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo

@Database(entities = [PaymentInfo::class], version = 2)
abstract class PaymentInfoDataBase : RoomDatabase() {

	companion object {

		const val NAME = "PAYMENT_INFO_DATA_BASE_NAME"
	}

	abstract fun getPaymentInfoByMonth(): PaymentScheduleDao
}
