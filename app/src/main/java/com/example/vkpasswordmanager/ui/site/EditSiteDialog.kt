package com.example.vkpasswordmanager.ui.site

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkpasswordmanager.ui.masterKey.MasterPasswordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSiteDialog(
    state: SiteUIState,
    onEvent: (SiteUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val masterPasswordViewModel: MasterPasswordViewModel = viewModel()

    AlertDialog(
        modifier = modifier,
        onDismissRequest = {onEvent(SiteUiEvent.HideDialog)},
        title = { Text(text = "Edit site") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.name,
                    onValueChange = { onEvent(SiteUiEvent.SetName(it)) },
                    placeholder = {
                        Text(text = "Site Name")
                    }
                )

                TextField(
                    value = state.url,
                    onValueChange = { onEvent(SiteUiEvent.SetUrl(it)) },
                    placeholder = {
                        Text(text = "Url")
                    }
                )

                TextField(
                    value = state.password,
                    onValueChange = {
                        state.password = masterPasswordViewModel.encryptPassword(state.password).toString()
                        onEvent(SiteUiEvent.SetPassword(it))
                                    },
                    placeholder = {
                        Text(text = "Password")
                    }
                )
                onEvent(SiteUiEvent.SetId(state.id))
                onEvent(SiteUiEvent.SetMostUsed(state.mostUsed))
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Button(onClick = { onEvent(SiteUiEvent.UpsertSite)}) {
                    Text(text = "Edit Site")
                }
            }
        },
    )
}

