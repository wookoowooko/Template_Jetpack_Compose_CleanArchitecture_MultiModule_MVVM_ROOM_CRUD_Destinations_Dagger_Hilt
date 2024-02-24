package io.genry.template.presenter.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.genry.template.domain.models.ItemModel
import io.genry.template.domain.usecases.CreateNewItemUseCase
import io.genry.template.domain.usecases.DeleteItemByIdUseCase
import io.genry.template.domain.usecases.GetAllItemsUseCase
import io.genry.template.domain.usecases.UpdateItemByIdUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(

    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val createNewItemUseCase: CreateNewItemUseCase,
    private val updateItemUseCase: UpdateItemByIdUseCase,
    private val deleteItemUseCase: DeleteItemByIdUseCase

) : ViewModel() {

    val allItems = getAllItemsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    var item: MutableState<ItemModel?> = mutableStateOf(null)
        private set


    fun setItem(newItem: ItemModel) {
        item.value = newItem
    }


    fun createNewItem() {
        viewModelScope.launch {
            item.value?.let { createNewItemUseCase.execute(it) }
        }
    }

    fun updateItem() {
        viewModelScope.launch {
            item.value?.let { updateItemUseCase.execute(it) }
        }
    }

    fun deleteItem() {
        viewModelScope.launch {
            item.value?.let { deleteItemUseCase.execute(it) }
        }
    }

}