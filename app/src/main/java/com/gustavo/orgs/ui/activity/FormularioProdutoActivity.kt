package com.gustavo.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.gustavo.orgs.R
import com.gustavo.orgs.dao.ProdutosDAO
import com.gustavo.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(
    R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoSalvar()

    }

    private fun configuraBotaoSalvar() {
        val botaoSalvarFormulario = findViewById<Button>(R.id.formulario_produto_salvar)
        val dao = ProdutosDAO()
        botaoSalvarFormulario.setOnClickListener {
            val produtoCriado = criaProduto()


            dao.addProduto(produtoCriado)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.formulario_produto_nome)
        val nome = campoNome.text.toString()

        val campoDesc = findViewById<EditText>(R.id.formulario_produto_descricao)
        val descricao = campoDesc.text.toString()

        val campoValor = findViewById<EditText>(R.id.formulario_produto_valor)
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        val produtoCriado = Produto(
            nome,
            descricao,
            valor
        )
        return produtoCriado
    }
}