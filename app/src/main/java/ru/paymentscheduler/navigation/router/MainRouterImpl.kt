package ru.paymentscheduler.navigation.router

import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.paymentscheduler.navigation.NavigationTree
import ru.paymentscheduler.presentation.MainRouter

class MainRouterImpl(
	private val navController: NavController,
) : MainRouter {

	override fun openDetail(id: Int) {
		TODO("Not yet implemented")
	}

	override fun createPaymentSchedule() {
		navController.navigate(NavigationTree.Create.name)
	}
}