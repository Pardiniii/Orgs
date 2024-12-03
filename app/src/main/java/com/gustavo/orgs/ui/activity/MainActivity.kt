package com.gustavo.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gustavo.orgs.R
import com.gustavo.orgs.model.Produto
import com.gustavo.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto(nome = "teste",
                desc = "teste desc",
                valor = BigDecimal("19.99")
        ),
        Produto(nome = "teste 1",
            desc = "teste desc 1",
            valor = BigDecimal("29.99")
        ),
        Produto(nome = "teste 2",
            desc = "teste desc 2",
            valor = BigDecimal("39.99")
        ),
        ))

        val fab = findViewById<FloatingActionButton>(R.id.btn_vai_para_formulario)
        fab.setOnClickListener{
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
    }
