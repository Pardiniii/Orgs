package com.gustavo.orgs.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.gustavo.orgs.R
import com.gustavo.orgs.dao.ProdutosDAO
import com.gustavo.orgs.databinding.ActivityFormularioProdutoBinding
import com.gustavo.orgs.databinding.FormularioImagemBinding
import com.gustavo.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()

        binding.fomrularioProdutoImagem.setOnClickListener{
            val bindingFormularioImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormularioImagem.formularioImagemBtnCarregar.setOnClickListener{
                val url = bindingFormularioImagem.formularioImagemUrl.text.toString()
                bindingFormularioImagem.formularioImagemImageview.load(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingFormularioImagem.root)
                .setPositiveButton("Confirmar") {_, _ ->
                    url = bindingFormularioImagem.formularioImagemUrl.text.toString()
                    binding.fomrularioProdutoImagem.load(url)
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
            valor,
            this.url
        )
        return produtoCriado
    }
}