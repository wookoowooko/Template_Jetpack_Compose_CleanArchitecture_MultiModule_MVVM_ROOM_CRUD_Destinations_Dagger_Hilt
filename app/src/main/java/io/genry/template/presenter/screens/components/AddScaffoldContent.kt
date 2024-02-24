package io.genry.template.presenter.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.genry.template.domain.models.ItemModel
import io.genry.template.presenter.viewmodels.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScaffoldContent(
    scope: CoroutineScope,
    sheetState: SheetState,
    showBottomSheet: MutableState<Boolean>,
    viewModel: SharedViewModel
) {
    val (title, setTitle) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }

    val newItem = ItemModel(title = title, description = description)

    Text(
        text = "Title",
        color = Color.Black
    )
    TextField(
        value = title,
        onValueChange = { titleChanged ->
            setTitle(titleChanged)
        }
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Description",
        color = Color.Black
    )
    TextField(
        value = description,
        onValueChange = { descriptionChanged ->
            setDescription(descriptionChanged)
        }
    )
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = {
        viewModel.setItem(newItem)
        viewModel.createNewItem()
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet.value = false
            }
        }
    }) {
        Text("Done")
    }
}
