package com.gustavo.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gustavo.orgs.R
import com.gustavo.orgs.dao.ProdutosDAO
import com.gustavo.orgs.databinding.ActivityFormularioProdutoBinding
import com.gustavo.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()

        binding.fomrularioProdutoImagem.setOnClickListener{
            AlertDialog.Builder(this)
                .setView(R.layout.formulario_imagem)
                .setPositiveButton("Confirmar") {_, _ ->

                }
                .setNegativeButton("Cancelar") {_, _ ->

                }
                .show()

        }

    }

    private fun configuraBotaoSalvar() {
        val botaoSalvarFormulario = binding.formularioProdutoSalvar
        val dao = ProdutosDAO()
        botaoSalvarFormulario.setOnClickListener {
            val produtoCriado = criaProduto()


            dao.addProduto(produtoCriado)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.formularioProdutoNome
        val nome = campoNome.text.toString()

        val campoDesc = binding.formularioProdutoDescricao
        val descricao = campoDesc.text.toString()

        val campoValor = binding.formularioProdutoValor
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