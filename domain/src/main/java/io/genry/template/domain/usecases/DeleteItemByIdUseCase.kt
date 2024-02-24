package io.genry.template.domain.usecases

import io.genry.template.domain.models.ItemModel
import io.genry.template.domain.repositories.IDataSource
import javax.inject.Inject

class DeleteItemByIdUseCase @Inject constructor (
    private val iDataSource: IDataSource
) {
    suspend fun execute(item:ItemModel) = iDataSource.deleteItem(item)
}