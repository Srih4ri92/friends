package com.sri.friends.signUp


import com.sri.friends.domain.user.InMemoryUserCatalog
import com.sri.friends.domain.user.UserRepository
import com.sri.friends.domain.validation.CredentialsValidationResult
import com.sri.friends.domain.validation.RegexCredentialsValidator
import com.sri.friends.signUp.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class CredentialValidationTest {


    @ParameterizedTest
    @CsvSource(
        "email",
        "''",
        "'     '",
        "1q@c",
        "12qqw@",
        "qw@da."
    )
    fun invalidEmail(email: String) {
        val viewModel = SignUpViewModel(
            RegexCredentialsValidator(),
            UserRepository(InMemoryUserCatalog())
        )
        viewModel.createAccount(email, ":password:", ":about:")
        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }

    @ParameterizedTest
    @CsvSource(
        "email",
        "''",
        "'     '",
        "1q@c",
        "12qqw@",
        "qw@da.c",
        "qwertyui",
        "123456789",

        )
    fun invalidPassword(password: String) {
        val viewModel = SignUpViewModel(
            RegexCredentialsValidator(),
            UserRepository(InMemoryUserCatalog())
        )
        viewModel.createAccount("test@email.com", password, ":about:")
        assertEquals(SignUpState.BadPassword, viewModel.signUpState.value)
    }

    @Test
    fun validCredentials() {
        val credentialsValidator = RegexCredentialsValidator()
        val validationResult = credentialsValidator.validate("test@email.com", "qw12!@QWqw@!12")
        assertEquals(CredentialsValidationResult.Valid, validationResult)
    }
}