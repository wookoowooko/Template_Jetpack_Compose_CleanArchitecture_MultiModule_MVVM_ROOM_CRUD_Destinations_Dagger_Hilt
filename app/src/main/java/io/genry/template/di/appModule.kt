package io.genry.template.di


import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.genry.template.data.database.AppDatabase
import io.genry.template.data.database.dao.ItemDAO
import io.genry.template.data.repositoriesImplemetations.DataSourceImplementation
import io.genry.template.domain.repositories.IDataSource
import io.genry.template.domain.usecases.CreateNewItemUseCase
import io.genry.template.domain.usecases.DeleteItemByIdUseCase
import io.genry.template.domain.usecases.GetAllItemsUseCase
import io.genry.template.domain.usecases.UpdateItemByIdUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO items_table (title, description) VALUES ('title 1', 'description 1')")
                db.execSQL("INSERT INTO items_table (title, description) VALUES ('title 2', 'description 2')")
                db.execSQL("INSERT INTO items_table (title, description) VALUES ('title 3', 'description 3')")
                db.execSQL("INSERT INTO items_table (title, description) VALUES ('title 4', 'description 4')")
                db.execSQL("INSERT INTO items_table (title, description) VALUES ('title 5', 'description 5')")
            }
        }
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemDao(appDatabase: AppDatabase): ItemDAO {
        return appDatabase.itemDao()
    }

    @Provides
    @Singleton
    fun provideIDataSource(dataSourceImplementation: DataSourceImplementation): IDataSource {
        return dataSourceImplementation
    }

    @Provides
    @Singleton
    fun provideDataSourceImplementation(itemDao: ItemDAO): DataSourceImplementation {
        return DataSourceImplementation(itemDao)
    }


    @Provides
    @Singleton
    fun provideGetAllItemsUseCase(iDataSource: IDataSource): GetAllItemsUseCase {
        return GetAllItemsUseCase(iDataSource)
    }

    @Provides
    @Singleton
    fun provideCreateNewItemUseCase(iDataSource: IDataSource): CreateNewItemUseCase {
        return CreateNewItemUseCase(iDataSource)
    }

    @Provides
    @Singleton
    fun provideUpdateItemByIdUseCase(iDataSource: IDataSource): UpdateItemByIdUseCase {
        return UpdateItemByIdUseCase(iDataSource)
    }

    @Provides
    @Singleton
    fun provideDeleteItemByIdUseCase(iDataSource: IDataSource): DeleteItemByIdUseCase {
        return DeleteItemByIdUseCase(iDataSource)
    }

}

