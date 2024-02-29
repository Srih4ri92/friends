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
            typeEmail("hari@email.com")
            typePassword("password@123")
            submit()
        } verify{
            timelineScreenIsPresent()
        }
    }
}