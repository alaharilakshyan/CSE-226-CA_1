package com.example.cse226.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SimpleBankingScreen() {
    val listState = rememberLazyListState()

    val showFab by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 3 }
    }

    var itemToDelete by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        floatingActionButton = {
            if (showFab) {
                FloatingActionButton(onClick = {}) {
                    Text("Filter")
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            TransactionList(listState = listState) { clickedIndex ->
                itemToDelete = clickedIndex
            }

            itemToDelete?.let { id ->
                AlertDialog(
                    onDismissRequest = { itemToDelete = null },
                    title = { Text("Confirm Delete") },
                    text = { Text("Delete Transaction #$id?") },
                    confirmButton = {
                        TextButton(onClick = { itemToDelete = null }) {
                            Text("Delete")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { itemToDelete = null }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TransactionList(
    listState: LazyListState,
    onDeleteClick: (Int) -> Unit
) {
    LazyColumn(state = listState) {
        items(50) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Transaction #$index")
                IconButton(onClick = { onDeleteClick(index) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionListPreview() {
    CSE226Theme {
        TransactionList(
            listState = rememberLazyListState(),
            onDeleteClick = {}
        )
    }
}