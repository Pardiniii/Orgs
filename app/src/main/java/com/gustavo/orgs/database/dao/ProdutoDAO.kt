package com.gustavo.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gustavo.orgs.model.Produto

@Dao
interface ProdutoDAO {

    @Query("Select * from Produto")
    fun buscaTodos() : List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insere(vararg produto: Produto)

    @Delete
    fun deleta(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Long?): Produto?
}