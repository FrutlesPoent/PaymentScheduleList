package ru.paymentscheduler.component.compose.navbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.paymentscheduler.component.compose.colors.Dark100

@Composable
fun NavbarColor() {
	val systemUiController = rememberSystemUiController()
	systemUiController.setNavigationBarColor(Dark100)
	systemUiController.navigationBarDarkContentEnabled = true
	systemUiController.setStatusBarColor(Dark100)
	SideEffect {
		systemUiController.setNavigationBarColor(
			color = Dark100,
		)
	}
}