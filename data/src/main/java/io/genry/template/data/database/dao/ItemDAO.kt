package io.genry.template.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.genry.template.data.database.dbo.ItemDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {

    @Query("SELECT * FROM items_table")
    fun getAllItems(): Flow<List<ItemDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewItem(itemDBO: ItemDBO)

    @Delete
    suspend fun deleteItem(itemDBO: ItemDBO)

    @Update
    suspend fun updateItem(itemDBO:ItemDBO)

}