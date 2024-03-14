package com.sri.friends.signUp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.sri.friends.MainActivity
import com.sri.friends.R

fun launchSignUpScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SignUpRobot.() ->Unit
): SignUpRobot{
    return SignUpRobot(rule = rule).apply(block = block)
}

class SignUpRobot(
    private val rule:AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    fun typeEmail(email: String) {
        val emailHint = rule.activity.getString(R.string.emailHint)
        rule.onNodeWithText(emailHint)
            .performTextInput(email)
    }

    fun typePassword(password: String) {
        val passwordHint = rule.activity.getString(R.string.passwordHint)
        rule.onNodeWithText(passwordHint)
            .performTextInput(password)
    }

    fun submit() {
        val submit = rule.activity.getString(R.string.submit)
        rule.onNodeWithText(submit)
            .performClick()
    }

    infix fun verify(
        block: SignUpScreenVerification.() -> Unit
    ): SignUpScreenVerification {
        return SignUpScreenVerification(rule).apply(block)
    }

}

class SignUpScreenVerification(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
){
    fun timelineScreenIsPresent(){
        val timeline = rule.activity.getString(R.string.timeline)
        rule.onNodeWithText(timeline)
            .assertIsDisplayed()
    }

    fun duplicateAccountErrorIsShown(){

        val duplicateAccountError = rule.activity.getString(R.string.duplicateAccountError)
        rule.onNodeWithText(duplicateAccountError)
            .assertIsDisplayed()
    }

}

