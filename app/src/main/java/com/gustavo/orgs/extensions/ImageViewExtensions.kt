package com.gustavo.orgs.extensions

import android.widget.ImageView
import coil.load
import com.gustavo.orgs.R

fun ImageView.tentaCarregarImagem(url: String?){
    load(url){
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}