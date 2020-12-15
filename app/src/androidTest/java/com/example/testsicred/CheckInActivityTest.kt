package com.example.testsicred

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.testsicred.views.CheckInActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CheckInActivityTest {

    @get:Rule
    val mActivityRule = ActivityTestRule(CheckInActivity::class.java)

    @Test
    fun ensureThatComponentsAreVisible() {
        onView(withId(R.id.txt_check_in_title))
            .check(matches(withText(R.string.txt_check_in_title)))

        onView(withId(R.id.til_email))
            .check(matches(isDisplayed()))

        onView(withId(R.id.txt_email_error))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.til_name))
            .check(matches(isDisplayed()))

        onView(withId(R.id.txt_name_error))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.btn_cancel))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_make_check_in))
            .check(matches(isDisplayed()))
    }
}