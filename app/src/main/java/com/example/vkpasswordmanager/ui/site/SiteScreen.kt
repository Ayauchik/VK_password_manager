package com.example.vkpasswordmanager.ui.site

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkpasswordmanager.ui.masterKey.CreateMasterKeyScreen
import com.example.vkpasswordmanager.ui.masterKey.MasterKeyDialog
import com.example.vkpasswordmanager.ui.masterKey.MasterPasswordViewModel
import com.example.vkpasswordmanager.ui.views.SiteCard
import kotlinx.coroutines.launch

@Composable
fun SiteScreen(
    viewModel: SiteViewModel = get(),
    actualMasterKey: String
   // masterKeyViewModel: MasterPasswordViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value
    val onEvent = viewModel::onEvent
    val masterPasswordViewModel: MasterPasswordViewModel = viewModel()
    var passwordVisible by remember { mutableStateOf(false) }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
//
//    if (showDialog) {
//        MasterKeyDialog(
//            onDismiss = { setShowDialog(false) },
//            actualMasterKey = actualMasterKey
//        ) { isValidMasterKey ->
//            passwordVisible = isValidMasterKey // Update password visibility
//        }
//    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(SiteUiEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "adding site"
                )
            }

        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->

        if (state.isAddingSite) {
            AddSiteDialog(state = state, onEvent = onEvent)
        }

        if(state.isEditingSite){
            MasterKeyDialog(
                onDismiss = { setShowDialog(false) },
                actualMasterKey = actualMasterKey
            ) { isValidMasterKey ->
                passwordVisible = isValidMasterKey // Update password visibility
            }
            if(passwordVisible){
                EditSiteDialog(state = state, onEvent = onEvent)
            }
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Your master key: $actualMasterKey")
                    IconButton(onClick = {
                        masterPasswordViewModel.deleteMasterKey()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "deleting site"
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortType.entries.forEach { sortType ->
                        Row(
                            modifier = Modifier.clickable {
                                onEvent(SiteUiEvent.SortSites(sortType))
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = { onEvent(SiteUiEvent.SortSites(sortType)) }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }

            items(state.sites) { site ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    SiteCard(
                        actualMasterKey = actualMasterKey,
                        site = site,
                        passwordVisibility = passwordVisible
                    )

                    IconButton(onClick = {
                        onEvent(SiteUiEvent.ShowDialogEdit(site))
                        setShowDialog(true)
                        //onEvent(SiteUiEvent.UpsertSite(site))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit site"
                        )
                    }

                    IconButton(onClick = {
                        onEvent(SiteUiEvent.DeleteSite(site))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "deleting site"
                        )
                    }
                }
            }
        }
    }
}