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
fun RequiredLaunchPermissionDialog (
    permission: String,
    permissionState: PermissionState,
) {
    val context = LocalContext.current
    val permissionLabel = stringResource(
        context.packageManager.getPermissionInfo(permission, 0).labelRes
    )

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Required Permission!") },
        text = { Text(text = permissionLabel) },
        confirmButton = {
            Button(onClick = {
                permissionState.launchPermissionRequest()
            }) {
                Text(text = "Launch")
            }
        },
    )
}