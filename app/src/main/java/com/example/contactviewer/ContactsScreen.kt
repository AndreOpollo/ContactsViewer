package com.example.contactviewer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ContactsScreen(){
    val context = LocalContext.current
    var contacts by remember { mutableStateOf<List<Contact>>(emptyList()) }

    RequestContactsPermission {
        contacts = fetchContacts(context)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ){
        items(contacts){
            contact->
            Column(
                modifier = Modifier.fillMaxWidth() .padding(vertical = 8.dp)
            ){
                Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                Text(text = contact.phone, style = MaterialTheme.typography.bodyMedium)
                HorizontalDivider(modifier = Modifier.padding(top=8.dp))

            }
        }

    }

}