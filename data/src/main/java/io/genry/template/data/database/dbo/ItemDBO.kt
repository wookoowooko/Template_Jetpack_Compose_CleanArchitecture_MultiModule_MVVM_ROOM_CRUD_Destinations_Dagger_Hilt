package io.genry.template.data.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class ItemDBO(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("description") val description: String
)