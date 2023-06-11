package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OptionalMultiplePermissionScreen(
    permissions: List<String>
) {

    var showRational by remember { mutableStateOf(true) }
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)

    if (multiplePermissionsState.shouldShowRationale) {
        if(showRational) {
            OptionalRationalPermissionsDialog(
                permissions,
                dismissCallback = {showRational = false}
            )
        }

    } else if (!multiplePermissionsState.allPermissionsGranted)  {
        SideEffect {
            multiplePermissionsState.launchMultiplePermissionRequest()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Optional Permission status: ${getPermissionStatusText(multiplePermissionsState)}")
    }
}

@OptIn(ExperimentalPermissionsApi::class)
fun getPermissionStatusText(multiplePermissionsState: MultiplePermissionsState): String {
    var permissionStatusText = "\n"
    for(permissionStatus in multiplePermissionsState.permissions) {
        val statusText = if (permissionStatus.status.isGranted) {
            "Granted\n"
        } else {
            "Denied\n"
        }
        permissionStatusText += "${permissionStatus.permission}: $statusText"
    }

    return permissionStatusText
}

