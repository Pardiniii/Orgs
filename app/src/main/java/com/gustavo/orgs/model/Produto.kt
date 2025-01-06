package com.gustavo.orgs.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity(tableName = "Produto")
@Parcelize
data class Produto(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "Nome_produto")
    val nome : String,

    @ColumnInfo(name = "Desc_produto")
    val desc : String,

    @ColumnInfo(name = "valor_produto")
    val valor : BigDecimal,

    @ColumnInfo(name = "Imagem_produto")
    val imagem : String? = null
) : Parcelable


