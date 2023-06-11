package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequiredMultiplePermissionScreen(
    permissions: List<String>
) {

    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)
    val permissionStatusText by remember(multiplePermissionsState) {
        derivedStateOf {
            getPermissionStatusText(multiplePermissionsState)
        }
    }

    if (multiplePermissionsState.shouldShowRationale) {
        RequiredRationalPermissionsDialog(
            permissions
        )

    } else if (!multiplePermissionsState.allPermissionsGranted)  {
        RequiredLaunchPermissionsDialog(
            permissions,
            multiplePermissionsState,
        )

        SideEffect {
            multiplePermissionsState.launchMultiplePermissionRequest()
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

