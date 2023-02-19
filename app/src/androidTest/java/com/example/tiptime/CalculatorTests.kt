package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CalculatorTests {

    //SE INICIALIZA LA ACTIVIDAD A TESTEAR
    @get:Rule()
    val activity: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip(){ //user calculate 20 percent tip show correct information

        /*Se obtiene la caja de texto y se escribe 50.00, ademas se oculta el teclado*/
        onView(
            withId(
                R.id.cost_of_service_edit_text
            )
        ).perform(
            typeText(
                "50.00"
            )
        ).perform(
            closeSoftKeyboard()
        )

        /*Hacemos click en el botón de calcular la propina*/
        onView(
            withId(
                R.id.calculate_button
            )
        ).perform(
            click()
        )

        /*Verificamos que una vez realizado lo anterior el resultado de la propina sea el adecuado*/
        onView(
            withId(
                R.id.tip_result
            )
        ).check(
            matches(
                withText(
                    containsString(
                        "$10.00"
                    )
                )
            )
        )


    }

    @Test
    fun calculate_18_percent_tip_without_round(){
        //INGRESAMOS EL VALOR DE LA CUENTA
        onView(
            withId(
                R.id.cost_of_service_edit_text
            )
        ).perform(
            typeText(
                "80.00"
            )
        ).perform(
            closeSoftKeyboard()
        )

        //SELECIONAMOS LA SEGUNDA OPCION DEL RADIO BUTTON
        onView(
            withId(
                R.id.option_eighteen_percent
            )
        ).perform(
            click()
        )

        //DESACTIVAMOS LA OPCION DE REDONDEO
        onView(
            withId(
                R.id.round_up_switch
            )
        ).perform(
            click()
        )

        //CLICK EN EL BOTÓN DE CALCULAR
        onView(
            withId(
                R.id.calculate_button
            )
        ).perform(
            click()
        )

        //VALIDAMOS SI ES CORRECTO EL VALOR
        onView(
            withId(
                R.id.tip_result
            )
        ).check(
            matches(
                withText(
                    containsString(
                        "$14.40"
                    )
                )
            )
        )


    }

}