package com.sri.friends.domain.user

import com.sri.friends.domain.exceptions.DuplicateAccountException

class InMemoryUserCatalog(
    private val userForPassword: MutableMap<String, MutableList<User>> = mutableMapOf<String, MutableList<User>>()
){
    fun createUser(
        email: String,
        about: String,
        password: String
    ): User {
        checkUserExist(email)
        val userId = generateUserId(email)
        val user = User(userId, email, about)
        saveUser(password, user)
        return user
    }

    private fun checkUserExist(email: String) {
        if(userForPassword.values.flatten().any { it.email == email }) {
            throw DuplicateAccountException()
        }
    }

    private fun generateUserId(email: String) = email.takeWhile { it != '@' } + "Id"

    private fun saveUser(password: String, user: User) {
        userForPassword.getOrPut(password, ::mutableListOf).add(user)
    }
}