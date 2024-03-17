package com.example.vkpasswordmanager.ui.masterKey

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CreateMasterKeyScreen(onMasterKeyCreated: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(8.dp)
        )
        Button(onClick = { onMasterKeyCreated(text) }) {
            Text("Create Master Key")
        }
    }
}

@Composable
fun SuccessScreen(
    masterKey: String?,
//    onMasterKeyUpdate: (String) -> Unit,
    onDeleteMasterKey: () -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Successfully created master key: $masterKey",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(8.dp)
        )
//        Button(onClick = { onMasterKeyUpdate(text) }) {
//            Text("Update Master Key")
//        }
        Button(
            onClick = onDeleteMasterKey,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete Master Key")
        }
    }
}
