package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OptionalLaunchPermissionsDialog (
    permissions: List<String>,
    multiplePermissionState: MultiplePermissionsState,
    dismissCallback: () -> Unit
) {
    val context = LocalContext.current
    var permissionLabels = ""
    for(permission in permissions) {
        val permissionLabel = stringResource(
            context.packageManager.getPermissionInfo(permission, 0).labelRes
        )
        permissionLabels += "$permissionLabel \n"
    }


    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Permission Required!") },
        text = { Text(text = permissionLabels) },
        confirmButton = {
            Button(onClick = {
                multiplePermissionState.launchMultiplePermissionRequest()
            }) {
                Text(text = "Launch")
            }
        },
        dismissButton = {
            Button(onClick = {
                dismissCallback()
            }) {
                Text(text = "Cancel")
            }
        }
    )
}