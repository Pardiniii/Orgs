package com.gustavo.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gustavo.orgs.databinding.ActivityDetalhesProdutoBinding
import com.gustavo.orgs.extensions.formataParaMoedaBrasileira
import com.gustavo.orgs.extensions.tentaCarregarImagem
import com.gustavo.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let {
                produtoCarregado -> preencheCampos(produtoCarregado)
        } //?: finish()  //finaliza a activity caso nao encontre o produto
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            detalhesImagemProduto.tentaCarregarImagem(produtoCarregado.imagem)
            detalhesNomeProduto.text = produtoCarregado.nome
            detalhesDescricaoProduto.text = produtoCarregado.desc
            detalhesValorProduto.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
            }
        }



}