package ru.paymentscheduler.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import com.google.android.material.R.*
import ru.paymentscheduler.domain.entity.Month

class ExposedDropDownStateHolder(
	currentMonth: Month,
) {

	var firstOpened by mutableStateOf(true)
	var enabled by mutableStateOf(false)
	var value by mutableStateOf(currentMonth.fullName)
	var selectedIndex by mutableStateOf(-1)
	var size by mutableStateOf(Size.Zero)
	val icon: Int
		@Composable get() = if (enabled) {
			drawable.mtrl_ic_arrow_drop_up
		} else {
			drawable.mtrl_ic_arrow_drop_down
		}

	val items = Month.toList()

	fun onEnabled(newValue: Boolean) {
		enabled = newValue
	}

	fun onSelectedIndex(newValue: Int) {
		selectedIndex = newValue
		value = items[selectedIndex]
	}

	fun onSize(value: Size) {
		size = value
	}

	fun onFirstOpened(value: Boolean) {
		firstOpened = value
	}
}

@Composable
fun rememberStateHolder(currentMonth: Month) = remember {
	ExposedDropDownStateHolder(currentMonth)
}