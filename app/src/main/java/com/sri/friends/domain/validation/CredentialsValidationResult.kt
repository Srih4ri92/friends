package com.sri.friends.domain.validation

sealed class CredentialsValidationResult {
    data object InvalidEmail: CredentialsValidationResult()
    data object InvalidPassword: CredentialsValidationResult()
    data object Valid: CredentialsValidationResult()

}
