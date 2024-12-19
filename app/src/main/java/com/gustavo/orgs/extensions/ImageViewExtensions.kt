package com.gustavo.orgs.extensions

import android.widget.ImageView
import coil.load
import com.gustavo.orgs.R

fun ImageView.tentaCarregarImagem(
    url: String? = null,
    fallback: Int = R.drawable.imagem_padrao){
    load(url){
        fallback(fallback)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}