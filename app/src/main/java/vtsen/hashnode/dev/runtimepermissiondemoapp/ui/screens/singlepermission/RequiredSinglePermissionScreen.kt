package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.singlepermission

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
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequiredSinglePermissionScreen(
    permission: String,
    requiredPermission: Boolean,
) {

    var permissionStatusText by remember { mutableStateOf("") }
    val permissionState = rememberPermissionState(permission)

    if (permissionState.status.isGranted) {
        permissionStatusText = "Granted"
    }
    else if (permissionState.status.shouldShowRationale)
    {
        permissionStatusText = "Denied"

        if (requiredPermission) {
            RequiredPermissionDialog(permission)
        } else {
            OptionalRationalPermissionDialog(
                permission,
                dismissCallback = {}
            )
        }
    }
    else {
        permissionStatusText = "N/A"
        OptionalRationalPermissionDialog(permission, dismissCallback = {})
        SideEffect {
            permissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Permission status: $permissionStatusText")
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            permissionState.launchPermissionRequest()
        }) {
            if (requiredPermission) {
                Text(text = "Request Single Required Permission")
            } else {
                Text(text = "Request Single Optional Permission")

            }
        }
    }
}

