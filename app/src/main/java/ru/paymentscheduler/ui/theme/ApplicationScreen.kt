package ru.paymentscheduler.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.java.KoinJavaComponent.inject
import ru.paymentscheduler.navigation.NavigationTree
import ru.paymentscheduler.ui.MainScreen

@Composable
fun ApplicationScreen() {
	val navController by inject<NavHostController>(NavController::class.java)
	NavHost(navController = navController, startDestination = NavigationTree.Main.name) {
		composable(NavigationTree.Main.name) { MainScreen() }
	}

}