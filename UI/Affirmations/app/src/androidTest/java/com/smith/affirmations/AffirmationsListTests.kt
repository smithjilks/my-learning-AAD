package com.smith.affirmations

import android.support.test.espresso.contrib.RecyclerViewActions
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AffirmationsListTests {

    @get: Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    fun testRecyclerView() {
    onView(withId(R.id.recycler_view)).perform(
    RecyclerViewActions
    .scrollToPosition<RecyclerView.ViewHolder>(9))
    )

    onView(withText(R.string.affirmation10))
    .check(matches(isDisplayed()))
    }


}