package com.sri.friends.signUp.state

sealed class SignUpState {
    data object BadEmail: SignUpState()
    data object BadPassword: SignUpState()
}
