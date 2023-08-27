package ru.paymentscheduler.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaymentInfo(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	val description: String,
	val amount: String,
	val month: Month,
)
