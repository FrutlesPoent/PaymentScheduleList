package ru.paymentsheduler.feature.create.ui

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel
import ru.paymentscheduler.component.compose.navbar.NavbarColor
import ru.paymentsheduler.feature.create.presentation.CreatePaymentSchedulerViewModel

@Composable
fun CreatePaymentScheduler(
	viewModel: CreatePaymentSchedulerViewModel = getViewModel(),
) {
	NavbarColor()

}