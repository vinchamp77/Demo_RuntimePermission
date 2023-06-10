package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions

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
fun MultiplePermissionsScreen() {

    var permissionStatusText by remember { mutableStateOf("N/A") }
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
        val permissions = mutableListOf(
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).toList()
        MultiplePermissionsDialog(permissions, true) { permissionsStatus ->

            permissionStatusText = "\n"
            for(permissionStatus in permissionsStatus) {
                val statusText = if (permissionStatus.status.isGranted) {
                    "Granted\n"
                } else {
                    "Denied\n"
                }
                permissionStatusText += "${permissionStatus.permission}: $statusText"
            }

            showOptionalPermissionDialog = false
        }
    }
    if(showOptionalPermissionDialog)
    {
        val permissions = mutableListOf(
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).toList()
        MultiplePermissionsDialog(permissions, false) { permissionsStatus ->

            permissionStatusText = "\n"
            for(permissionStatus in permissionsStatus) {
                val statusText = if (permissionStatus.status.isGranted) {
                    "Granted\n"
                } else {
                    "Denied\n"
                }
                permissionStatusText += "${permissionStatus.permission}: $statusText"
            }

            showOptionalPermissionDialog = false
        }
    }

}
