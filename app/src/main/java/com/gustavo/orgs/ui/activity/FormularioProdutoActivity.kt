package com.gustavo.orgs.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gustavo.orgs.R
import com.gustavo.orgs.dao.ProdutosDAO
import com.gustavo.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(
    R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val botaoSalvarFormulario = findViewById<Button>(R.id.btn_salvar)
        botaoSalvarFormulario.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.campo_nome)
            val nome = campoNome.text.toString()

            val campoDesc = findViewById<EditText>(R.id.campo_descricao)
            val descricao = campoDesc.text.toString()

            val campoValor = findViewById<EditText>(R.id.campo_valor)
            val valorEmTexto = campoValor.text.toString()
            val valor = if (valorEmTexto.isBlank()){
                BigDecimal.ZERO
                } else{
                    BigDecimal(valorEmTexto)
            }

            val produtoCriado = Produto(
                nome,
                descricao,
                valor
            )
            Log.i("FormularioProduto", "onCreate: $produtoCriado")
            val dao = ProdutosDAO()
            dao.addProduto(produtoCriado)
            Log.i("FormularioProduto", "onCreate: ${dao.buscaTodos()}")
            finish()
        }

    }
}