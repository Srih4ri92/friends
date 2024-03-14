package com.sri.friends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sri.friends.signUp.SignUpViewModel
import com.sri.friends.signUp.SingUpScreen
import com.sri.friends.timeline.TimelineScreen
import com.sri.friends.ui.theme.FriendsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signUpViewModel: SignUpViewModel by viewModel()
        setContent {
            val navController = rememberNavController()
            FriendsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = SIGN_UP_SCREEN){
                        composable(SIGN_UP_SCREEN){
                            SingUpScreen(
                                onSignedUp = {
                                    navController.navigate(TIMELINE_SCREEN)
                                },
                                signUpViewModel = signUpViewModel
                            )
                        }
                        composable(TIMELINE_SCREEN){
                            TimelineScreen()
                        }
                    }

                }
            }
        }
    }



    companion object {
        private const val SIGN_UP_SCREEN = "SignUpScreen"
        private const val TIMELINE_SCREEN = "Timeline"
    }
}

