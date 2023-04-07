package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(shouldShowRequestPermissionRationale: (String) -> Boolean) {

    var showPermissionDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showPermissionDialog = false
            showPermissionDialog = true
        }) {
            Text(text = "Request permission")
        }
    }

    if(showPermissionDialog)
    {
        PermissionDialog(
            permissionStr = Manifest.permission.CALL_PHONE,
            shouldShowRequestPermissionRationale
        )
    }
}
