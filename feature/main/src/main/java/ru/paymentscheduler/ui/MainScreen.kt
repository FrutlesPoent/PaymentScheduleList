package ru.paymentscheduler.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel
import ru.paymentscheduler.component.compose.colors.Dark100
import ru.paymentscheduler.component.compose.colors.White75
import ru.paymentscheduler.component.compose.navbar.NavbarColor
import ru.paymentscheduler.domain.entity.Month
import ru.paymentscheduler.domain.entity.PaymentInfo
import ru.paymentscheduler.presentation.ContentType
import ru.paymentscheduler.presentation.MainState
import ru.paymentscheduler.presentation.MainViewModel
import ru.paymentscheduler.presentation.rememberStateHolder
import java.lang.Thread.sleep

@Composable
fun MainScreen(
	viewModel: MainViewModel = getViewModel(),
) {
	NavbarColor()
	viewModel.start()

	val state by viewModel.state.collectAsState(
		initial = MainState.Initial,
	)

	Surface(
		color = MaterialTheme.colors.primaryVariant,
		modifier = Modifier
			.fillMaxSize()
	) {

		when (state) {
			is MainState.Content -> {
				RenderContent(state as MainState.Content, viewModel::changeMonth)
			}

			MainState.Error      -> Unit
			MainState.Initial    -> Unit
			MainState.Loading    -> Loading()
		}

	}

}

@Composable
fun RenderContent(state: MainState.Content, changeMonth: (String) -> Unit) {
	when (state.contentType) {
		is ContentType.Content -> {
			AnimatedVisibility(visible = true) {
				RenderContentData(state.contentType.paymentList, state.contentType.currentMonth, changeMonth)
			}

		}

		is ContentType.Empty   -> {
			AnimatedVisibility(visible = true) {
				RenderEmptyContent(state.contentType.currentMonth, changeMonth)
			}
		}
	}
}

@Composable
fun RenderContentData(paymentList: List<PaymentInfo>, month: Month, changeMonth: (String) -> Unit) {
	Row {
		AppBar(onBackClick = ::nothing, currentMonth = month, changeMonth)

		RecyclerView(paymentList = paymentList, navigateToDetail = ::nothing2)
	}
}

@Composable
fun RenderEmptyContent(month: Month, changeMonth: (String) -> Unit) {
	Column {
		AppBar(onBackClick = ::nothing, currentMonth = month, changeMonth = changeMonth)
		Row(
			modifier = Modifier
				.fillMaxHeight()
				.padding(horizontal = 30.dp),
		) {

			Text(
				modifier = Modifier
					.fillMaxSize()
					.align(alignment = Alignment.CenterVertically)
					.padding(top = 20.dp),
				text = "В данном месяце нет запланированных трат", color = White75, style = MaterialTheme.typography.h6,
				textAlign = TextAlign.Center,
			)
		}
	}
}

private fun nothing(): Unit {

}

private fun nothing2(id: Int) {

}

@Composable
private fun RecyclerView(paymentList: List<PaymentInfo>, navigateToDetail: (Int) -> Unit) {
	LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
		items(items = paymentList) {
			ListItem(
				id = it.id,
				title = "test",
				smallDescription = it.description,
				amount = it.amount,
				onDetailClicked = navigateToDetail,
			)
		}
	}
}

@Composable
private fun ListItem(id: Int, title: String, smallDescription: String, amount: String, onDetailClicked: (Int) -> Unit) {
	Surface(
		color = MaterialTheme.colors.secondary,
		modifier = Modifier
			.padding(vertical = 4.dp, horizontal = 8.dp)
			.fillMaxSize()
			.clickable { onDetailClicked(id) }
	) {
		Row {
			Text(
				text = title,
				style = MaterialTheme.typography.h4,
				modifier = Modifier.fillMaxWidth()
			)
			Column {
				Text(
					text = smallDescription,
					style = MaterialTheme.typography.body1,
					modifier = Modifier.fillMaxWidth()
				)
				Text(
					text = amount,
					style = MaterialTheme.typography.h2,
					modifier = Modifier.fillMaxWidth()
				)
			}

		}

	}
}

@Composable
fun AppBar(onBackClick: () -> Unit, currentMonth: Month, changeMonth: (String) -> Unit) {
	val stateHolder = rememberStateHolder(currentMonth)

	TopAppBar(
		backgroundColor = Dark100,
		modifier = Modifier
			.fillMaxWidth()
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.background(Dark100), horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box {
				OutlinedTextField(
					enabled = false,
					colors = TextFieldDefaults.outlinedTextFieldColors(
						textColor = White,
						unfocusedBorderColor = White
					),
					value = stateHolder.value,
					onValueChange = {},
					trailingIcon = {
						Icon(
							painter = painterResource(id = stateHolder.icon),
							null,
							Modifier.clickable {
								stateHolder.onEnabled(!stateHolder.enabled)
							}
						)
					},
					modifier = Modifier
						.onGloballyPositioned {
							stateHolder.onSize(it.size.toSize())
						}
				)
				DropdownMenu(
					expanded = stateHolder.enabled, onDismissRequest = { stateHolder.onEnabled(false) },
					modifier = Modifier.width(with(LocalDensity.current) { stateHolder.size.width.toDp() })
				) {
					stateHolder.items.forEachIndexed { index, it ->
						DropdownMenuItem(onClick = {
							stateHolder.onSelectedIndex(index)
							stateHolder.onEnabled(false)
							stateHolder.onFirstOpened(false)
							changeMonth(it)
						}) {
							Text(text = it)
						}
					}
				}
			}
		}
	}
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Loading() {
	Box(modifier = Modifier.fillMaxSize()) {
		CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
	}

	val keyboardController = LocalSoftwareKeyboardController.current
	keyboardController?.hide()
}
