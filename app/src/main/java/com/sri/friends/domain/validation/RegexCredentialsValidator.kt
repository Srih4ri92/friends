package com.sri.friends.domain.validation

import java.util.regex.Pattern


class RegexCredentialsValidator {

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"
        private const val PASSWORD_REGEX =
            "^(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\",.<>/?`~\\\\|]).{2,}(?=.*\\d.*\\d).{8,}\$"
    }

    private val emailPattern: Pattern
        get() {
            return Pattern.compile(EMAIL_REGEX)
        }

    private val passwordPattern: Pattern
        get() {
            return Pattern.compile(PASSWORD_REGEX)
        }

    fun validate(
        email: String, password: String
    ): CredentialsValidationResult {

        val result = if (!emailPattern.matcher(email).matches()) {
            CredentialsValidationResult.InvalidEmail
        } else if (!passwordPattern.matcher(password).matches()) {
            CredentialsValidationResult.InvalidPassword
        } else CredentialsValidationResult.Valid
        return result
    }
}