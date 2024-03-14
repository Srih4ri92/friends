package com.sri.friends.signUp

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sri.friends.R
import com.sri.friends.signUp.state.SignUpState

@Composable
fun SingUpScreen(
    signUpViewModel: SignUpViewModel, onSignedUp: () -> Unit = {}
) {
    val state = signUpViewModel.signUpState.collectAsState().value

    var email: String by remember {
        mutableStateOf("")
    }

    var password: String by remember {
        mutableStateOf("")
    }

    var about: String by remember {
        mutableStateOf("")
    }

    when (state) {
        SignUpState.BadEmail, SignUpState.BadPassword, SignUpState.DuplicateAccount -> {
            InfoMessage(R.string.duplicateAccountError)
        }

        is SignUpState.SignedUp -> {
            onSignedUp()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {
        SignUpScreenTitle(title = R.string.createAccount)
        EmailField(value = email, onValueChange = {
            email = it
        })
        PasswordField(value = password, onValueChange = {
            password = it
        })
        AboutField(value = about, onValueChange = {
            about = it
        })
        Button(
            onClick = {
                signUpViewModel.createAccount(
                    email = email, about = "", password = password
                )
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.submit), fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun InfoMessage(@StringRes stringResource: Int) {
    Text(
        text = stringResource(stringResource), style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun EmailField(
    value: String, onValueChange: (String) -> Unit
) {
    OutlinedTextField(value = value, label = {
        Text(
            text = stringResource(id = R.string.emailHint), modifier = Modifier.alpha(0.5f)
        )
    }, onValueChange = {
        onValueChange(it)
    }, modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PasswordField(
    value: String, onValueChange: (String) -> Unit
) {
    var isPasswordVisible: Boolean by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(value = value, label = {
        Text(
            text = stringResource(id = R.string.passwordHint), modifier = Modifier.alpha(0.5f)
        )
    }, trailingIcon = {
        VisibilityToggle(isPasswordVisible = isPasswordVisible, onClick = {
            isPasswordVisible = !isPasswordVisible
        })
    }, visualTransformation = if (isPasswordVisible) VisualTransformation.None
    else PasswordVisualTransformation(), onValueChange = {
        onValueChange(it)
    }, modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AboutField(
    value: String, onValueChange: (String) -> Unit
) {
    OutlinedTextField(value = value, label = {
        Text(
            text = stringResource(id = R.string.aboutHint), modifier = Modifier.alpha(0.5f)
        )
    }, onValueChange = {
        onValueChange(it)
    }, modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun VisibilityToggle(
    isPasswordVisible: Boolean, onClick: () -> Unit
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_password_hide)
            else painterResource(id = R.drawable.ic_password_visible),
            contentDescription = stringResource(id = R.string.showHidePassword)
        )
    }
}

@Composable
private fun SignUpScreenTitle(title: Int) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
@Preview(
    device = Devices.PIXEL_4_XL, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true
)
fun SingUpScreenPreviewDark() {
    SingUpScreen(viewModel())
}

@Composable
@Preview(
    device = Devices.PIXEL_4_XL, uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true
)
fun SingUpScreenPreviewLight() {
    SingUpScreen(viewModel())
}