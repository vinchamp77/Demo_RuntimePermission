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
fun OptionalSinglePermissionScreen(
    permission: String,
) {
    var permissionStatusText by remember { mutableStateOf("") }
    val permissionState = rememberPermissionState(permission)

    var launchPermissionDialog by remember { mutableStateOf(true) }
    var showRationale by remember { mutableStateOf(true) }

    if (permissionState.status.isGranted) {
        permissionStatusText = "Granted"
    }

    else if (permissionState.status.shouldShowRationale) {
        permissionStatusText = "Denied"

        if(showRationale) {
            OptionalRationalPermissionDialog(
                permission,
                dismissCallback = {showRationale = false}
            )
        }

    } else {
        permissionStatusText = "N/A"
        if (launchPermissionDialog) {
            OptionalLaunchPermissionDialog(
                permission,
                permissionState,
                dismissCallback = { launchPermissionDialog = false}
            )

            SideEffect {
                permissionState.launchPermissionRequest()
            }
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Optional Permission status: $permissionStatusText")
    }
}


