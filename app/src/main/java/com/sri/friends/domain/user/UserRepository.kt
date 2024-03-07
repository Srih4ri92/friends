package com.sri.friends.domain.user

import com.sri.friends.domain.exceptions.DuplicateAccountException
import com.sri.friends.signUp.state.SignUpState

class UserRepository(private val inMemoryUserCatalog: InMemoryUserCatalog) {
    fun signUpState(
        email: String,
        about: String,
        password: String
    ) = try {
        val user = inMemoryUserCatalog.createUser(email, about, password)
        SignUpState.SignedUp(user)
    } catch (duplicateAccountException: DuplicateAccountException) {
        SignUpState.DuplicateAccount
    }
}