package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.singlepermission

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OptionalLaunchPermissionDialog (
    permission: String,
    permissionState: PermissionState,
    dismissCallback: () -> Unit
) {
    val context = LocalContext.current
    val permissionLabel = stringResource(
        context.packageManager.getPermissionInfo(permission, 0).labelRes
    )

    AlertDialog(
        onDismissRequest = { dismissCallback()},
        title = { Text(text = "Permission Required!") },
        text = { Text(text = permissionLabel) },
        confirmButton = {
            Button(onClick = {
                permissionState.launchPermissionRequest()
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