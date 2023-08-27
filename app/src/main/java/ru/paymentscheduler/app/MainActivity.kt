package ru.paymentscheduler.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import org.koin.java.KoinJavaComponent
import ru.paymentscheduler.di.PaymentInfoDaoModule
import ru.paymentscheduler.di.PaymentInfoDatabaseModule
import ru.paymentscheduler.di.PaymentInfoModule
import ru.paymentscheduler.di.mainModule
import ru.paymentscheduler.di.routersModule
import ru.paymentscheduler.ui.theme.ApplicationScreen
import ru.paymentscheduler.ui.theme.PaymentSchedulerTheme

class MainActivity : FragmentActivity() {

	private companion object {

		val modules = listOf(
			PaymentInfoDatabaseModule,
			PaymentInfoDaoModule,
			PaymentInfoModule,
			mainModule,
			routersModule,
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		KoinJavaComponent.getKoin().loadModules(modules = modules)
		setContent {
			PaymentSchedulerTheme {
				Column(
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.CenterHorizontally,
				) {
					ApplicationScreen()
				}
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()

		KoinJavaComponent.getKoin().unloadModules(modules)
	}
}