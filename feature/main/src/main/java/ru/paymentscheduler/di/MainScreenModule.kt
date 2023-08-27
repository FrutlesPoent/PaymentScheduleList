package ru.paymentscheduler.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.paymentscheduler.presentation.MainViewModel

val mainModule = module {
	viewModel {
		MainViewModel(
			getPaymentSchedulerByMonthUseCase = get(),
		)
	}
}