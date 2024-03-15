package com.sri.friends.signUp
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sri.friends.MainActivity
import com.sri.friends.domain.user.InMemoryUserCatalog
import com.sri.friends.domain.user.UserCatalog
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class SignUpTest {

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()


    private val signUpModule = module {
        factory<UserCatalog> { InMemoryUserCatalog() }
    }

    @Before
    fun setUp() {
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
        val signedUpUserEmail = "anna@email.com"
        val signedUpUserPassword = "QWerty12#$"
        replaceUserCatalogWith(InMemoryUserCatalog().apply {
            createUser(signedUpUserEmail, signedUpUserPassword, "")
        })
        launchSignUpScreen(signUpTestRule){
            typeEmail(signedUpUserEmail)
            typePassword(signedUpUserPassword)
            submit()
        }verify {
            duplicateAccountErrorIsShown()
        }
    }

    @After
    fun tearDown() {
        replaceUserCatalogWith(InMemoryUserCatalog())
    }

    private fun replaceUserCatalogWith(userCatalog: UserCatalog) {
        val replaceModule = module {
            factory { userCatalog }
        }
        loadKoinModules(replaceModule)
    }
}