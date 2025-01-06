package com.gustavo.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gustavo.orgs.database.AppDataBase
import com.gustavo.orgs.databinding.ActivityListaProdutosBinding
import com.gustavo.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity(){

    private val adapter = ListaProdutosAdapter(context = this)
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
