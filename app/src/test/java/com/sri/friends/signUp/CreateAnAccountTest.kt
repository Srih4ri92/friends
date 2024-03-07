package com.sri.friends.signUp;

import com.sri.friends.domain.user.User
import com.sri.friends.domain.validation.RegexCredentialsValidator
import com.sri.friends.signUp.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateAnAccountTest {
    @Test
    fun accountCreated(){
        val maya = User("mayaId","maya@emali.com","about maya")
        val viewModel = SignUpViewModel(RegexCredentialsValidator())
        viewModel.createAccount(maya.email,"QWerty12#$",maya.about)
        assertEquals(SignUpState.SignedUp(maya),viewModel.signUpState.value)
    }

    @Test
    fun createAnotherAccount(){
        val bob = User("bobId","bob@email.com","about bob")
        val viewModel = SignUpViewModel(RegexCredentialsValidator())
        viewModel.createAccount(bob.email,"QWerty12#$",bob.about)
        assertEquals(SignUpState.SignedUp(bob),viewModel.signUpState.value)
    }

    @Test
    fun createDuplicateAccount(){
        val anna = User("annaId","anna@email.com","about anna")
        val viewModel = SignUpViewModel(RegexCredentialsValidator())
        viewModel.createAccount(anna.email,"QWerty12#$",anna.about)
        assertEquals(SignUpState.DuplicateAccount,viewModel.signUpState.value)
    }
}
