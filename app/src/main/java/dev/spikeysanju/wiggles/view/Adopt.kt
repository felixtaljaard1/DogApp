package dev.spikeysanju.wiggles.view

import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Right
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.spikeysanju.wiggles.R
import dev.spikeysanju.wiggles.navigation.Screen

@Composable
fun AdoptScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adopt a Dog") },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = colorResource(id = R.color.text),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = colorResource(id = R.color.text)
                    )
                }
            )
        },

        content = {
            Column() {
                AdoptView()

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Submit")
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdoptView() {

    val focusManager = LocalFocusManager.current
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordConfimation by remember {
        mutableStateOf("")
    }


    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(6.dp)
    ) {

        item {
            AdoptTextView(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text = "First Name") },
                focusManager = focusManager
            )
        }
        item {
            AdoptTextView(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Last Name") },
                focusManager = focusManager

            )
        }
        item {
            AdoptTextView(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email Address") },
                focusManager = focusManager

            )
        }
        item {
            AdoptTextView(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text(text = "Phone Number") },
                focusManager = focusManager

            )
        }
        item {
            AdoptTextView(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                focusManager = focusManager

            )
        }
        item {
            AdoptTextView(
                value = passwordConfimation,
                onValueChange = { passwordConfimation = it },
                label = { Text(text = "Confirm Password") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                focusManager = focusManager,
                visualTransformation = PasswordVisualTransformation(),


                )
        }
    }
}

@Composable
fun AdoptTextView(

    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    focusManager: FocusManager,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions(onNext = {
        if (!focusManager.moveFocus(FocusDirection.Right)) {

            focusManager.moveFocus(FocusDirection.Down)
            focusManager.moveFocus(FocusDirection.Left)

        }
    }),
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.surface,
            cursorColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = MaterialTheme.colors.primary,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Gray,
        ),
        label = { label() },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AdoptScreenPreview() {
    AdoptScreen(navController = rememberAnimatedNavController())
}