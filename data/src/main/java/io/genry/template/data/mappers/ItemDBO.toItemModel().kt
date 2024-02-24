package io.genry.template.data.mappers

import io.genry.template.data.database.dbo.ItemDBO
import io.genry.template.domain.models.ItemModel

fun ItemDBO.toItemModel(): ItemModel {
    return ItemModel(
        id = id,
        title = title,
        description = description
    )
}