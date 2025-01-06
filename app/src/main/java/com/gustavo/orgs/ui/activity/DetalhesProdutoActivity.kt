package com.gustavo.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gustavo.orgs.R
import com.gustavo.orgs.database.AppDataBase
import com.gustavo.orgs.databinding.ActivityDetalhesProdutoBinding
import com.gustavo.orgs.extensions.formataParaMoedaBrasileira
import com.gustavo.orgs.extensions.tentaCarregarImagem
import com.gustavo.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            val db = AppDataBase.instancia(this)
            val produtoDao = db.produtoDAO()
            when(item.itemId){
                R.id.menu_detalhes_produto_excluir -> {
                    produtoDao.deleta(produto)
                    finish()
                }
                R.id.menu_detalhes_produto_editar -> {
                    Intent(this, FormularioProdutoActivity::class.java).apply {
                        putExtra(CHAVE_PRODUTO, produto)
                        startActivity(this)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            produto = produtoCarregado
            preencheCampos(produtoCarregado)
        } ?: finish()  //finaliza a activity caso nao encontre o produto
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