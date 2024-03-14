package com.sri.friends.app

import com.sri.friends.domain.user.InMemoryUserCatalog
import com.sri.friends.domain.user.UserRepository
import com.sri.friends.domain.validation.RegexCredentialsValidator
import com.sri.friends.signUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    single { InMemoryUserCatalog() }
    factory { RegexCredentialsValidator() }
    factory { UserRepository(inMemoryUserCatalog = get()) }

    viewModel {
        SignUpViewModel(
            regexCredentialsValidator = get(),
            userRepository = get()
        )
    }
}