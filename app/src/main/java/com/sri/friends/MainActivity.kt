package com.sri.friends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sri.friends.signUp.SingUpScreen
import com.sri.friends.timeline.TimelineScreen
import com.sri.friends.ui.theme.FriendsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.background),
                                onSignedUp = {
                                    navController.navigate(TIMELINE_SCREEN)
                                }
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

