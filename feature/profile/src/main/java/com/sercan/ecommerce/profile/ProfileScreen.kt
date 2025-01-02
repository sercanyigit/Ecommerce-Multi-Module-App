package com.sercan.ecommerce.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Profil",
                        style = MaterialTheme.typography.titleLarge
                    ) 
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Kullanıcı Adı",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "kullanici@email.com",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            ListItem(
                headlineContent = { 
                    Text(
                        text = "Siparişlerim",
                        style = MaterialTheme.typography.titleMedium
                    ) 
                },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )

            ListItem(
                headlineContent = { 
                    Text(
                        text = "Adreslerim",
                        style = MaterialTheme.typography.titleMedium
                    ) 
                },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )

            ListItem(
                headlineContent = { 
                    Text(
                        text = "Ödeme Yöntemlerim",
                        style = MaterialTheme.typography.titleMedium
                    ) 
                },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )

            ListItem(
                headlineContent = { 
                    Text(
                        text = "Ayarlar",
                        style = MaterialTheme.typography.titleMedium
                    ) 
                },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )
        }
    }
} 