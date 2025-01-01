package com.example.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profil") }
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
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "kullanici@email.com",
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            ListItem(
                headlineContent = { Text("Siparişlerim") },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )
            
            ListItem(
                headlineContent = { Text("Adreslerim") },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )
            
            ListItem(
                headlineContent = { Text("Ödeme Yöntemlerim") },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )
            
            ListItem(
                headlineContent = { Text("Ayarlar") },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )
        }
    }
} 