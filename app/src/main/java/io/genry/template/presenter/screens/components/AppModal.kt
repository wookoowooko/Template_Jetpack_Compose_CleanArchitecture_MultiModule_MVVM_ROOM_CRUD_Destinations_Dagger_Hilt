package io.genry.template.presenter.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.genry.template.domain.models.ItemModel
import io.genry.template.presenter.viewmodels.SharedViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppModal(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    currentAction: MutableState<ActionType?>,
    scope: CoroutineScope,
    viewModel: SharedViewModel,
    item: ItemModel?
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(0.8f),
        containerColor = Color.White,
        onDismissRequest = {
            showBottomSheet.value = false
        },
        sheetState = sheetState
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (currentAction.value) {
                    ActionType.ADD -> {
                        AddScaffoldContent(
                            scope, sheetState, showBottomSheet, viewModel
                        )
                    }

                    ActionType.EDIT -> {
                        EditScaffoldContent(
                            scope, sheetState, showBottomSheet, viewModel, item
                        )
                    }

                    ActionType.DELETE -> {
                        DeleteScaffoldContent(
                            item,
                            viewModel, scope,
                            sheetState,
                            showBottomSheet
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}




