package com.gustavo.orgs.dao

import com.gustavo.orgs.model.Produto

class ProdutosDAO {

    private val produtos = mutableListOf<Produto>()

    fun addProduto(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto>{
        return produtos.toList()
    }
}