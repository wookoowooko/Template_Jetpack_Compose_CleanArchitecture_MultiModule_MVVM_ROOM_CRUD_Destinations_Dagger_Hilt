package io.genry.template.data.repositoriesImplemetations


import io.genry.template.data.database.dao.ItemDAO
import io.genry.template.data.mappers.toItemDBO
import io.genry.template.data.mappers.toItemModel
import io.genry.template.domain.models.ItemModel
import io.genry.template.domain.repositories.IDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataSourceImplementation @Inject constructor(
    private val itemDao: ItemDAO
) : IDataSource {

    override fun getAllItems(): Flow<List<ItemModel>> {
        return itemDao.getAllItems().map { list ->
            list.map { itemDBO ->
                itemDBO.toItemModel()
            }
        }
    }

    override suspend fun createNewItem(item: ItemModel) {
        itemDao.createNewItem(item.toItemDBO())
    }

    override suspend fun deleteItem(item: ItemModel) {
        itemDao.deleteItem(item.toItemDBO())
    }

    override suspend fun updateItem(item: ItemModel) {
        itemDao.updateItem(item.toItemDBO())
    }

}