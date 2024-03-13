package com.sri.friends.signUp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sri.friends.MainActivity
import org.junit.Rule
import org.junit.Test

class SignUpTest {

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun performSignUp(){
        launchSignUpScreen(signUpTestRule){
            typeEmail("anna@email.com")
            typePassword("QWerty12#$")
            submit()
        } verify{
            timelineScreenIsPresent()
        }
    }
}