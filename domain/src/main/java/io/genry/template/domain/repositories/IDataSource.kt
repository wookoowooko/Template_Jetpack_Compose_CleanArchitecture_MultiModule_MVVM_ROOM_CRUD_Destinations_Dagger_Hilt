package io.genry.template.domain.repositories

import io.genry.template.domain.models.ItemModel
import kotlinx.coroutines.flow.Flow


interface IDataSource {

    fun getAllItems(): Flow<List<ItemModel>>

    suspend fun createNewItem(item: ItemModel)

    suspend fun deleteItem(item: ItemModel)

    suspend fun updateItem(item: ItemModel)

}