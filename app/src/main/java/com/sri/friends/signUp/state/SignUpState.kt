package com.sri.friends.signUp.state

import com.sri.friends.domain.user.User

sealed class SignUpState {
    data object BadEmail: SignUpState()
    data object BadPassword: SignUpState()
    data object DuplicateAccount: SignUpState()
    data class SignedUp(val user: User): SignUpState()
}
