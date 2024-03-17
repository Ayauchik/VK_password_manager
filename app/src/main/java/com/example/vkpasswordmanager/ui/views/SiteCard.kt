package com.example.vkpasswordmanager.ui.views

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.vkpasswordmanager.domain.models.Site
import com.example.vkpasswordmanager.domain.use_case.UpsertSiteUseCase
import com.example.vkpasswordmanager.ui.masterKey.MasterKeyDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiteCard(
    site: Site,
    actualMasterKey: String?,
    passwordVisibility: Boolean
) {
    var iconUrl by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(passwordVisibility) }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    if (showDialog) {
        MasterKeyDialog(
            onDismiss = { setShowDialog(false) },
            actualMasterKey = actualMasterKey
        ) { isValidMasterKey ->
            passwordVisible = isValidMasterKey // Update password visibility
        }
    }

    val url = site.url
    LaunchedEffect(url) {
        try {
            val doc = withContext(Dispatchers.IO) { Jsoup.connect(url).get() }
            val icon = doc.select("link[rel~=(?i)^(shortcut|icon)$]").firstOrNull()
            val iconHref = icon?.attr("href")
            if (iconHref != null) {
                iconUrl = iconHref
            } else {
                iconUrl = "$url/favicon.ico"
            }
        } catch (e: IOException) {
            Log.e("SiteCard", "Error fetching icon URL: $e")
        }
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.8f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = {
            setShowDialog(true)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            if (iconUrl != null) {
                val borderWidth = 4.dp
                Image(
                    painter = rememberAsyncImagePainter(iconUrl),
                    contentDescription = "icon of website",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .border(BorderStroke(borderWidth, Color.LightGray), CircleShape)
                        .padding(borderWidth)
                        .clip(CircleShape),
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Loading", style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))

            Column {
                Text(
                    text = site.name,
                    style = MaterialTheme.typography.displaySmall
                )

                Spacer(modifier = Modifier.size(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Password: ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = if (passwordVisible) site.password else "*".repeat(site.password.length),
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }
        }
    }
}
