package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {

    var permissionStatusText by remember { mutableStateOf("") }
    var showRequiredPermissionDialog by remember { mutableStateOf(false) }
    var showOptionalPermissionDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Permission status: $permissionStatusText")
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            showRequiredPermissionDialog = true
        }) {
            Text(text = "Request Required Permission")
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            showOptionalPermissionDialog = true
        }) {
            Text(text = "Request Optional Permission")
        }
    }

    if(showRequiredPermissionDialog)
    {
        PermissionDialog(Manifest.permission.CALL_PHONE, true) { permissionStatus ->
            permissionStatusText = if (permissionStatus.isGranted) {
                "Granted"
            } else {
                "Denied"
            }
            showRequiredPermissionDialog = false
        }
    }

    if(showOptionalPermissionDialog)
    {
        PermissionDialog(Manifest.permission.CALL_PHONE, false) { permissionStatus ->
            permissionStatusText = if (permissionStatus.isGranted) {
                "Granted"
            } else {
                "Denied"
            }
            showOptionalPermissionDialog = false
        }
    }

}

