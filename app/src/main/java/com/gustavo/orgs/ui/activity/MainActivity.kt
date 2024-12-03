package com.gustavo.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gustavo.orgs.R
import com.gustavo.orgs.dao.ProdutosDAO
import com.gustavo.orgs.model.Produto
import com.gustavo.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val dao = ProdutosDAO()
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

        val fab = findViewById<FloatingActionButton>(R.id.btn_vai_para_formulario)
        fab.setOnClickListener{
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
    }
