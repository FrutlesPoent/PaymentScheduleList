package ru.paymentscheduler.di

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.paymentscheduler.navigation.router.MainRouterImpl
import ru.paymentscheduler.presentation.MainRouter

val routersModule = module {
	single<NavController> {
		NavHostController(androidContext()).apply {
			navigatorProvider.addNavigator(ComposeNavigator())
			navigatorProvider.addNavigator(DialogNavigator())
		}
	}
	factory<MainRouter> { MainRouterImpl(get()) }
}