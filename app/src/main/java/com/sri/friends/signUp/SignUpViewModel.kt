package com.sri.friends.signUp

import com.sri.friends.domain.user.User
import com.sri.friends.domain.validation.CredentialsValidationResult
import com.sri.friends.domain.validation.RegexCredentialsValidator
import com.sri.friends.signUp.state.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow

class SignUpViewModel(
    private val regexCredentialsValidator: RegexCredentialsValidator
) {

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.BadEmail)
    val signUpState = _signUpState

    fun createAccount(
        email: String, password: String, about: String
    ) {

        val result = regexCredentialsValidator.validate(email, password)

        _signUpState.value = when (result) {
            CredentialsValidationResult.InvalidEmail ->
                SignUpState.BadEmail

            CredentialsValidationResult.InvalidPassword ->
                SignUpState.BadPassword

            CredentialsValidationResult.Valid -> {
                if(email == "anna@email.com"){
                    SignUpState.DuplicateAccount
                }else {
                    val userId = email.takeWhile { it != '@' } + "Id"
                    val user = User(userId, email, about)
                    SignUpState.SignedUp(user)
                }
            }

        }


    }

}


