package io.genry.template.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.genry.template.data.database.dao.ItemDAO
import io.genry.template.data.database.dbo.ItemDBO

@Database(entities = [ItemDBO::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDAO
}
