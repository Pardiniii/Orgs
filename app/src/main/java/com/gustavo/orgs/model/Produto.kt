package com.gustavo.orgs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Produto(
    val nome : String,
    val desc : String,
    val valor : BigDecimal,
    val imagem : String? = null
) : Parcelable


