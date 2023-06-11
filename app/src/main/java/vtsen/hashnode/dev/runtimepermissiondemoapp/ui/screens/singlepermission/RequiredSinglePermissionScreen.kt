package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.singlepermission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequiredSinglePermissionScreen(
    permission: String,
) {

    var permissionStatusText by remember { mutableStateOf("") }
    val permissionState = rememberPermissionState(permission)

    if (permissionState.status.isGranted) {
        permissionStatusText = "Granted"

    } else if (permissionState.status.shouldShowRationale) {
        permissionStatusText = "Denied"
        RequiredRationalPermissionDialog(permission)

    } else {
        permissionStatusText = "N/A"
        RequiredLaunchPermissionDialog(permission, permissionState)

        SideEffect {
            permissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Required Permission status: $permissionStatusText")
    }
}

