package ru.paymentscheduler.presentation

import android.icu.util.Calendar
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo
import ru.paymentscheduler.domain.usecase.GetPaymentSchedulerByMonthUseCase
import ru.paymentscheduler.mvvm.BaseViewModel
import ru.paymentscheduler.mvvm.launch
import java.text.DateFormat

class MainViewModel(
	private val getPaymentSchedulerByMonthUseCase: GetPaymentSchedulerByMonthUseCase,
) : BaseViewModel() {

	private val _state = MutableStateFlow<MainState>(MainState.Initial)
	val state: Flow<MainState> = _state.asStateFlow()

	fun start() {
		viewModelScope.launch {
			if (_state.value == MainState.Initial) {
				_state.value = MainState.Loading
				val currentMonth = getCurrentMonth()
				val paymentsList = loadPayments(currentMonth)

				changeContentState(paymentsList, currentMonth)
			}
		}
	}

	fun changeMonth(month: String) {
		viewModelScope.launch {
			if (_state.value is MainState.Content) {
				_state.value = MainState.Loading
				val newMonth = Month.fromFullName(month)
				val paymentList = loadPayments(newMonth)
				changeContentState(paymentList, newMonth)
			}
		}
	}

	private fun changeContentState(paymentList: List<PaymentInfo>, month: Month) {
		if (paymentList.isNotEmpty()) {
			_state.value = MainState.Content(ContentType.Content(paymentList, month))
		} else {
			_state.value = MainState.Content(ContentType.Empty(month))
		}
	}

	private suspend fun loadPayments(month: Month): List<PaymentInfo> =
		getPaymentSchedulerByMonthUseCase(month)

	private fun getCurrentMonth(): Month =
		convertIdToMonth(Calendar.getInstance().get(Calendar.MONTH))

	private fun convertIdToMonth(id: Int): Month =
		Month.fromInt(id)
}