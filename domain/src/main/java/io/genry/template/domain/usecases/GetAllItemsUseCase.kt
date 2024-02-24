package io.genry.template.domain.usecases

import io.genry.template.domain.repositories.IDataSource
import javax.inject.Inject

class GetAllItemsUseCase @Inject constructor (
    private val iDataSource: IDataSource
) {
    operator fun invoke() = iDataSource.getAllItems()
}