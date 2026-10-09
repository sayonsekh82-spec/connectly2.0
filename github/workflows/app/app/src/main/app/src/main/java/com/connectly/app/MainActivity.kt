
package com.connectly.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ConnectlyApp()
            }
        }
    }
}

@Composable
fun ConnectlyApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf("Chats", "Calls", "Groups", "Profile")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Connectly",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                actions = {
                    TextButton(onClick = {}) {
                        Text("Settings")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Text(
                                when (index) {
                                    0 -> "💬"
                                    1 -> "📞"
                                    2 -> "👥"
                                    else -> "👤"
                                }
                            )
                        },
                        label = { Text(tab) }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when (selectedTab) {
                0 -> {
                    Text(
                        "Your messages",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(12.dp))

                    LazyColumn {
                        items(
                            listOf(
                                "Welcome to Connectly",
                                "Friends",
                                "Family Group",
                                "Work Group"
                            )
                        ) { chat ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment =
                                        Alignment.CenterVertically
                                ) {
                                    Text("💬")
                                    Spacer(Modifier.width(12.dp))
                                    Text(chat)
                                }
                            }
                        }
                    }
                }

                1 -> FeatureScreen(
                    "Audio & Video Calls",
                    "Calling features will be connected next."
                )

                2 -> FeatureScreen(
                    "Group Chats",
                    "Create and manage group conversations."
                )

                else -> FeatureScreen(
                    "Your Profile",
                    "Connectly account and profile settings."
                )
            }
        }
    }
}

@Composable
fun FeatureScreen(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(12.dp))
        Text(description)
    }
}
