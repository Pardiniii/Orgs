package com.gustavo.orgs.dao

import com.gustavo.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDAO {

    fun addProduto(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto>{
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto("Salada de frutas",
                "Banana, maçã, uva",
                BigDecimal("29.87"))
        )
    }
}