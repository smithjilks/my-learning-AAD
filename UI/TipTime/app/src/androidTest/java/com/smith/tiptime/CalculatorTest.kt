package com.smith.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTest {

    //this line starts the activity
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity:: class.java)

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$10.0"))))
    }

    @Test
    fun calculate_20_percent_tip_without_roundup() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("76.00"))
        onView(withId(R.id.roundup_switch)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("15.2"))))
    }

    @Test
    fun calculate_20_percent_tip_with_roundup() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("76.00"))
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("16"))))
    }
}