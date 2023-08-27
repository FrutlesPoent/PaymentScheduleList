package ru.paymentscheduler.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo

@Dao
interface PaymentScheduleDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertPaymentInfo(paymentInfo: PaymentInfo)

	@Query("SELECT * FROM paymentInfo WHERE month = :month")
	suspend fun getPaymentInfo(month: Month): List<PaymentInfo>
}