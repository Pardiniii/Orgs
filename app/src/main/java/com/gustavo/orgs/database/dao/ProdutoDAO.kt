package com.gustavo.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gustavo.orgs.model.Produto

@Dao
interface ProdutoDAO {

    @Query("Select * from Produto")
    fun buscaTodos() : List<Produto>

    @Insert
    fun insere(vararg produto: Produto)

    @Delete
    fun deleta(produto: Produto)
}