package com.danielimamura.apostadoranonimo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(onLoginClick: () -> Unit) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf<String?>(null) }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
    var confirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    fun submit() {
        nameError = if (name.isBlank()) "Informe seu nome." else null
        emailError = when {
            email.isBlank() -> "Informe seu e-mail."
            !emailPattern.matches(email.trim()) -> "Informe um e-mail válido."
            else -> null
        }
        passwordError = when {
            password.isBlank() -> "Informe sua senha."
            password.length < 6 -> "A senha deve ter pelo menos 6 caracteres."
            else -> null
        }
        confirmPasswordError = when {
            confirmPassword.isBlank() -> "Confirme sua senha."
            confirmPassword != password -> "As senhas não são iguais."
            else -> null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Criar conta",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.padding(20.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome") },
            singleLine = true,
            isError = nameError != null,
            supportingText = { nameError?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.padding(6.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("E-mail") },
            placeholder = { Text("seu@email.com") },
            singleLine = true,
            isError = emailError != null,
            supportingText = { emailError?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.padding(6.dp))
        PasswordField(
            value = password,
            label = "Senha",
            error = passwordError,
            visible = passwordVisible,
            onValueChange = {
                password = it
                passwordError = null
            },
            onToggleVisibility = { passwordVisible = !passwordVisible }
        )
        Spacer(modifier = Modifier.padding(6.dp))
        PasswordField(
            value = confirmPassword,
            label = "Confirmar senha",
            error = confirmPasswordError,
            visible = confirmPasswordVisible,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = null
            },
            onToggleVisibility = { confirmPasswordVisible = !confirmPasswordVisible },
            imeAction = ImeAction.Done
        )
        Spacer(modifier = Modifier.padding(14.dp))
        Button(
            onClick = ::submit,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Criar conta")
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = "Já possui uma conta? Entrar",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onLoginClick)
        )
    }
}

@Composable
private fun PasswordField(
    value: String,
    label: String,
    error: String?,
    visible: Boolean,
    onValueChange: (String) -> Unit,
    onToggleVisibility: () -> Unit,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = true,
        isError = error != null,
        supportingText = { error?.let { Text(it) } },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onToggleVisibility) {
                Icon(
                    imageVector = if (visible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = if (visible) "Ocultar senha" else "Mostrar senha"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        )
    )
}
