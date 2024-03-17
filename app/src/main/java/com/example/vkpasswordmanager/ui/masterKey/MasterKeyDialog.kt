package com.example.vkpasswordmanager.ui.masterKey

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.vkpasswordmanager.ui.site.SiteUiEvent
import com.example.vkpasswordmanager.ui.site.SiteViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MasterKeyDialog(
    onDismiss: () -> Unit,
    actualMasterKey: String?,
    onMasterKeyEntered: (Boolean) -> Unit
) {
    val siteViewModel: SiteViewModel = getViewModel()
    val onEvent = siteViewModel::onEvent

    var masterKey by remember { mutableStateOf("") }
    var isMasterKeyValid by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Enter Master Key") },
        confirmButton = {
            Button(
                onClick = {
                    isMasterKeyValid = masterKey == actualMasterKey
                    onMasterKeyEntered(isMasterKeyValid) // Update password visibility
                    onDismiss()
                    onEvent(SiteUiEvent.SetTruePasswordVisibility)
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Text("Cancel")
            }
        },
        text = {
            OutlinedTextField(
                value = masterKey,
                onValueChange = { masterKey = it },
                label = { Text("Master Key") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    )

//    return isMasterKeyValid
}
