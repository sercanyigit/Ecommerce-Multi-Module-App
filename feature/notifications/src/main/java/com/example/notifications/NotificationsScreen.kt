package com.example.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen() {
    val notifications = listOf(
        "Yeni ürünlerimiz geldi!",
        "Sepetinizdeki ürünlerde indirim var",
        "Siparişiniz kargoya verildi"
    )
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bildirimler") }
            )
        }
    ) { paddingValues ->
        if (notifications.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Henüz bildiriminiz bulunmamaktadır.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(notifications) { notification ->
                    ListItem(
                        headlineContent = { Text(notification) },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = null
                            )
                        }
                    )
                    Divider()
                }
            }
        }
    }
} 