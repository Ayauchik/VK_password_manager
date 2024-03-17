package com.example.vkpasswordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.vkpasswordmanager.ui.masterKey.CreateMasterKeyScreen
import com.example.vkpasswordmanager.ui.masterKey.MasterPasswordViewModel
import com.example.vkpasswordmanager.ui.site.SiteScreen
import com.example.vkpasswordmanager.ui.theme.VkPasswordManagerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkPasswordManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // SiteScreen()
                 AppContent()
                }   
            }
        }
    }
}



@Composable
fun AppContent(masterPasswordViewModel: MasterPasswordViewModel = viewModel()) {

    val scope = rememberCoroutineScope()

    val masterKey by masterPasswordViewModel.masterKeyFlow.collectAsState(null)
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    // Check if the master key exists
    if (masterKey != null) {
         //If the master key exists, navigate to the success screen
//        SuccessScreen(masterKey) {
//            scope.launch {
//                // masterPasswordViewModel.updateMasterKey(it)
//                masterPasswordViewModel.deleteMasterKey()
//            }
//        }
        SiteScreen(actualMasterKey = masterKey!!)

    } else {
        // If the master key does not exist, ask the user to create one
        CreateMasterKeyScreen { masterKey ->
            scope.launch {
                masterPasswordViewModel.createMasterKey(masterKey)
            }
        }
    }
//
//    if(masterKey == null){
//        CreateMasterKeyScreen { masterKey ->
//            scope.launch {
//                masterPasswordViewModel.createMasterKey(masterKey)
//            }
//        }
//    }
//
//    WebsiteCard(
//        name = "google",
//        url = "https://www.google.com/",
//        password = "something",
//        actualMasterKey = masterKey
//    )

//    // Check if the master key exists
//    if (masterPasswordViewModel.masterKeyExists) {
//        // If the master key exists, navigate to the success screen
//        SuccessScreen(masterPasswordViewModel.masterKey){
//            scope.launch {
//                masterPasswordViewModel.updateMasterKey(it)
//            }
//
//        }
//    } else {
//        // If the master key does not exist, ask the user to create one
//        CreateMasterKeyScreen { masterKey ->
//            scope.launch {
//                masterPasswordViewModel.createMasterKey(masterKey)
//            }
//        }
//    }
}