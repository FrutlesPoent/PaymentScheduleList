package ru.paymentscheduler.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.paymentscheduler.data.dao.PaymentScheduleDao
import ru.paymentscheduler.data.repository.RepositoryImpl
import ru.paymentscheduler.database.PaymentInfoDataBase
import ru.paymentscheduler.domain.repository.PaymentSchedulerRepository
import ru.paymentscheduler.domain.usecase.GetPaymentSchedulerByMonthUseCase

val PaymentInfoDatabaseModule = module {
	single<PaymentInfoDataBase> {
		Room.databaseBuilder(androidContext(), PaymentInfoDataBase::class.java, PaymentInfoDataBase.NAME)
			.fallbackToDestructiveMigration()
			.build()
	}
}

val PaymentInfoDaoModule = module {
	single<PaymentScheduleDao> { get<PaymentInfoDataBase>().getPaymentInfoByMonth() }
}

val PaymentInfoModule = module {
	single<PaymentSchedulerRepository> { RepositoryImpl(get()) }
	factory { GetPaymentSchedulerByMonthUseCase(get()) }
}
