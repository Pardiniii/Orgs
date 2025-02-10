package com.gustavo.orgs

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.gustavo.orgs.ui.activity.ListaProdutosActivity
import org.junit.Test

class ListaProdutoActivityTest {

    @Test
    fun VerificaTitulo(){
        launch(ListaProdutosActivity::class.java)
        onView(withText("Orgs"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun DeveSerCapazDeEditarProduto(){
        launch(ListaProdutosActivity::class.java)

        onView(withId(R.id.lista_produtos_btn_vai_para_formulario))
            .perform(click())

        onView(withId(R.id.formulario_produto_nome))
            .perform(
                typeText("Salada de frutas"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.formulario_produto_descricao))
            .perform(
                typeText("Varias frutas"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.formulario_produto_valor))
            .perform(
                typeText("9.90"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.btn_formulario_produto_salvar))
            .perform(
                click()
            )
    }
}