package com.gustavo.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gustavo.orgs.R
import com.gustavo.orgs.databinding.ProdutoItemBinding
import com.gustavo.orgs.extensions.tentaCarregarImagem
import com.gustavo.orgs.model.Produto
import java.text.NumberFormat
import java.util.Locale


class ListaProdutosAdapter(
    private val context : Context,
    produtos : List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(produto: Produto) {
            val nome = binding.nome
            nome.text = produto.nome
            val desc = binding.descricao
            desc.text = produto.desc
            val valor = binding.valor
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val valorFormatado = formatador.format(produto.valor)
            valor.text = valorFormatado

            val visibilidade = if(produto.imagem != null){
                View.VISIBLE
            }else{
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            binding.imageView.tentaCarregarImagem(produto.imagem)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()

    }
}
