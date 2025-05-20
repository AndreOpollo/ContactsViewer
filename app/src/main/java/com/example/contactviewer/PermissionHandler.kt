package com.example.contactviewer

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat


@Composable
fun RequestContactsPermission(onPermissionGranted:()->Unit){
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        isGranted->
        if(isGranted)onPermissionGranted()
    }

    LaunchedEffect(Unit) {
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED
        ){
            onPermissionGranted()
        }else {
            permissionLauncher.launch(
                Manifest.permission.READ_CONTACTS
            )
        }

    }

}