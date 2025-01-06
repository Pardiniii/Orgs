package com.gustavo.orgs.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.gustavo.orgs.dao.ProdutosDAO
import com.gustavo.orgs.database.AppDataBase
import com.gustavo.orgs.databinding.ActivityListaProdutosBinding
import com.gustavo.orgs.model.Produto
import com.gustavo.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class ListaProdutosActivity : AppCompatActivity(){

    private val dao = ProdutosDAO()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFAB()


    }

    override fun onResume() {
        super.onResume()
        val db = AppDataBase.instancia(this)

        val produtoDAO = db.produtoDAO()
        adapter.atualiza(produtoDAO.buscaTodos())
    }

    private fun configuraFAB() {
        val fab = binding.listaProdutosBtnVaiParaFormulario
        fab.setOnClickListener {
            vaiParaFormualrioProduto()
        }
    }

    private fun vaiParaFormualrioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.listaProdutosRecyclerview
        val dao = ProdutosDAO()
        recyclerView.adapter = adapter

        adapter.quandoClicaNoItem = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java)
                .apply {
            putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }


    }
}
