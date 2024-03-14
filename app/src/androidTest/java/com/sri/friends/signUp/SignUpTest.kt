package com.sri.friends.signUp
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sri.friends.MainActivity
import com.sri.friends.domain.user.InMemoryUserCatalog
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


class SignUpTest {

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()

    private val userCatalog = InMemoryUserCatalog()
    private val signUpModule = module {
        factory{
            userCatalog
        }
    }

    @Before
    fun setUp(){
        loadKoinModules(signUpModule)
    }



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

    @Test
    fun duplicateAccountTest(){
        val signedUpEmail = "anna@email.com"
        val signedUpPassword = "QWer!@344"
        userCatalog.createUser(signedUpEmail,signedUpPassword,"`")
        launchSignUpScreen(signUpTestRule){
            typeEmail(signedUpEmail)
            typePassword(signedUpPassword)
            submit()
        }verify {
            duplicateAccountErrorIsShown()
        }
    }

    @After
    fun tearDown(){
        val resetModule = module {
            single { InMemoryUserCatalog() }
        }
        loadKoinModules(resetModule)
    }
}