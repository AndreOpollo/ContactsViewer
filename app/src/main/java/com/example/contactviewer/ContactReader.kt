package com.example.contactviewer

import android.content.Context
import android.provider.ContactsContract

fun fetchContacts(context: Context):List<Contact>{
    val contactList = mutableListOf<Contact>()
    val queryUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    val projection = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )
    val cursor = context.contentResolver.query(
        queryUri,
        projection,
        null,
        null,
        "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
    )
    cursor?.use {cursor->
        val nameColumn = cursor.getColumnIndex(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        val phoneColumn = cursor.getColumnIndex(
            ContactsContract.CommonDataKinds.Phone.NUMBER)

        while(cursor.moveToNext()){
            val name = cursor.getString(nameColumn)?:"Unknown"
            val phone = cursor.getString(phoneColumn)?:"No number"
            contactList.add(Contact(
                name = name,
                phone = phone
            ))
        }
    }
    return contactList
}