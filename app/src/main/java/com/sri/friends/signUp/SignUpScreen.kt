package com.sri.friends.signUp

import android.content.res.Configuration
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
import com.sri.friends.R

@Composable
fun SingUpScreen(
    modifier: Modifier
){
    var email: String by remember {
        mutableStateOf("")
    }

    var password: String by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        SignUpScreenTitle(title = R.string.createAccount)
        EmailField(
            value = email,
            onValueChange = {
                email = it
            }
        )
        PasswordField(
            value = password,
            onValueChange = {
                password = it
            }
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.submit),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun EmailField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        label = {
            Text(
                text = stringResource(id = R.string.emailHint),
                modifier = Modifier.alpha(0.5f)
            )
        },
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisibile: Boolean by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = value,
        label = {
            Text(
                text = stringResource(id = R.string.passwordHint),
                modifier = Modifier.alpha(0.5f)
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                isPasswordVisibile = !isPasswordVisibile
            }) {
                Icon(
                    painter =
                        if(isPasswordVisibile)
                            painterResource(id = R.drawable.ic_password_hide)
                        else
                            painterResource(id = R.drawable.ic_password_visible)
                    ,
                    contentDescription = stringResource(id = R.string.showHidePassword)
                )
            }
        },
        visualTransformation = if(isPasswordVisibile)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth()
    )
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
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
fun SingUpScreenDarkPreview(){
    SingUpScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
}

@Composable
@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
fun SingUpScreenLightPreview(){
    SingUpScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
}