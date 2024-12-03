package com.gustavo.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gustavo.orgs.R
import com.gustavo.orgs.dao.ProdutosDAO
import com.gustavo.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos){

    private val dao = ProdutosDAO()
    private val adapter  =ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
        configuraRecyclerView()
        configuraFAB()
    }

    private fun configuraFAB() {
        val fab = findViewById<FloatingActionButton>(R.id.lista_produtos_btn_vai_para_formulario)
        fab.setOnClickListener {
            vaiParaFormualrioProduto()
        }
    }

    private fun vaiParaFormualrioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.lista_produtos_recyclerview)
        val dao = ProdutosDAO()
        recyclerView.adapter = adapter
    }
}
