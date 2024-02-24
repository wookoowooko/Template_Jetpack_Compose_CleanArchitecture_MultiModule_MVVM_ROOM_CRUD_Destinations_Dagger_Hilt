package io.genry.template.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.genry.template.presenter.screens.components.ActionType
import io.genry.template.presenter.screens.components.AppBottomBar
import io.genry.template.presenter.screens.components.AppModal
import io.genry.template.presenter.screens.components.BoxContent
import io.genry.template.presenter.viewmodels.SharedViewModel
import io.genry.template.utils.factories.getOrCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun YourStartScreen(
    navigator: DestinationsNavigator
) {

//    navigator.navigate(YourSecondScreenDestination.route)

    val viewModel = getOrCreateViewModel<SharedViewModel>()
    val allItems by viewModel.allItems.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet = remember { mutableStateOf(false) }
    var currentAction = remember { mutableStateOf<ActionType?>(null) }
    val item by viewModel.item

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AppBottomBar(
                onAddClicked = {
                    currentAction.value = ActionType.ADD
                    showBottomSheet.value = true
                })
        }) {
        if (showBottomSheet.value) {
            AppModal(
                showBottomSheet,
                sheetState,
                currentAction,
                scope, viewModel,
                item
            )
        }

        BoxContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
            viewModel,
            showBottomSheet,
            currentAction,
            allItems
        )
    }
}








