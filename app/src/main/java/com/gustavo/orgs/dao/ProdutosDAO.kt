package com.gustavo.orgs.dao

import com.gustavo.orgs.model.Produto

class ProdutosDAO {

    fun addProduto(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto>{
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}