package com.gustavo.orgs

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.gustavo.orgs.ui.activity.ListaProdutosActivity
import org.junit.Rule
import org.junit.Test

class ListaProdutoTelasTest {

    @get:Rule
    val rule = ActivityScenarioRule(ListaProdutosActivity::class.java)

    @Test
    fun VerificaTitulo() {
        onView(withText("Orgs"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun DeveSerCapazDeEditarProduto() {

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

        onView(withText("Salada de frutas"))
            .perform(
                click()
            )

        onView(withId(R.id.menu_detalhes_produto_editar))
            .perform(
                click()
            )

        onView(withId(R.id.formulario_produto_nome))
            .perform(
                clearText(),
                typeText("banana"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.formulario_produto_descricao))
            .perform(
                clearText(),
                typeText("Prata"),
                closeSoftKeyboard()
            )

        onView(withId(R.id.btn_formulario_produto_salvar))
            .perform(
                click()
            )

        Espresso.pressBack()
    }
}