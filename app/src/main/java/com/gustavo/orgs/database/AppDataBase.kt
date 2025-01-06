package com.gustavo.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gustavo.orgs.database.converter.Converters
import com.gustavo.orgs.database.dao.ProdutoDAO
import com.gustavo.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun produtoDAO() : ProdutoDAO

    companion object {
        fun instancia(context: Context) : AppDataBase{
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "orgs.db"
            ).allowMainThreadQueries()
                .build()
        }
    }
}