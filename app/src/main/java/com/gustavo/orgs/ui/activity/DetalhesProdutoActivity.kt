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

    private var produtoId: Long = 0L
    private var produto: Produto? = null
    val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDataBase.instancia(this).produtoDAO()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        buscaProduto()
    }

    private fun buscaProduto() {
        produto = produtoDao.buscaPorId(produtoId)
        produto?.let {
            preencheCampos(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when(item.itemId){
                R.id.menu_detalhes_produto_excluir -> {
                    produto?.let {
                        produtoDao.deleta(it)
                    }
                    finish()
                }
                R.id.menu_detalhes_produto_editar -> {
                    Intent(this, FormularioProdutoActivity::class.java).apply {
                        putExtra(CHAVE_PRODUTO_ID, produtoId)
                        startActivity(this)
                    }
                }
            }

        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)

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