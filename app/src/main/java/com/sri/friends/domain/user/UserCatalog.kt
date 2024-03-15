package com.sri.friends.domain.user

interface UserCatalog {

    fun createUser(
        email: String,
        about: String,
        password: String
    ):User
}
