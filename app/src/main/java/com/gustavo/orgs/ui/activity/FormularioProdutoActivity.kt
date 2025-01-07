package com.gustavo.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gustavo.orgs.database.AppDataBase
import com.gustavo.orgs.database.dao.ProdutoDAO
import com.gustavo.orgs.databinding.ActivityFormularioProdutoBinding
import com.gustavo.orgs.extensions.tentaCarregarImagem
import com.gustavo.orgs.model.Produto
import com.gustavo.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var produtoId = 0L


    private val produtoDAO: ProdutoDAO by lazy {
        val db = AppDataBase.instancia(this)
        db.produtoDAO()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configuraBotaoSalvar()

        binding.fomrularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this).mostra(url) { imagem ->
                url = imagem
                binding.fomrularioProdutoImagem.tentaCarregarImagem(url)
            }
        }
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        tentaBuscarProduto()
    }

    private fun tentaBuscarProduto() {
        produtoDAO.buscaPorId(produtoId)?.let {
            preencheCampos(it)
        }
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        title = "Alterar produto"
        url = produtoCarregado.imagem
        binding.fomrularioProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
        binding.formularioProdutoNome.setText(produtoCarregado.nome)
        binding.formularioProdutoDescricao.setText(produtoCarregado.desc)
        binding.formularioProdutoValor.setText(produtoCarregado.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvarFormulario = binding.formularioProdutoSalvar

        botaoSalvarFormulario.setOnClickListener {
            val produtoCriado = criaProduto()
            produtoDAO.insere(produtoCriado)
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
            id = produtoId,
            nome = nome,
            desc = descricao,
            valor = valor,
            imagem = url
        )
        return produtoCriado
    }
}