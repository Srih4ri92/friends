package com.sri.friends.signUp

import com.sri.friends.domain.user.InMemoryUserCatalog
import com.sri.friends.domain.user.User
import com.sri.friends.domain.user.UserRepository
import com.sri.friends.domain.validation.RegexCredentialsValidator
import com.sri.friends.signUp.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateAnAccountTest {

    private val viewModel = SignUpViewModel(
        RegexCredentialsValidator(),
        UserRepository(InMemoryUserCatalog())
    )

    @Test
    fun accountCreated() {
        val maya = User("mayaId", "maya@emali.com", "about maya")
        viewModel.createAccount(maya.email, "QWerty12#12$", maya.about)
        assertEquals(SignUpState.SignedUp(maya), viewModel.signUpState.value)
    }

    @Test
    fun createAnotherAccount() {
        val bob = User("bobId", "bob@email.com", "about bob")
        viewModel.createAccount(bob.email, "QWerty12#2$", bob.about)
        assertEquals(SignUpState.SignedUp(bob), viewModel.signUpState.value)
    }

    @Test
    fun createDuplicateAccount() {
        val anna = User("annaId", "anna@email.com", "about anna")
        val password = "QWerty12#$"
        val userForPassword = mutableMapOf(password to mutableListOf(anna))
        val viewModel = SignUpViewModel(
            RegexCredentialsValidator(),
            UserRepository(InMemoryUserCatalog(userForPassword))
        )
        viewModel.createAccount(anna.email, "QWerty12#$", anna.about)
        assertEquals(SignUpState.DuplicateAccount, viewModel.signUpState.value)
    }
}
