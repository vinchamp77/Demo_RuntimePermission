package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionDialog(
    permission: String,
    requiredPermission: Boolean,
    onPermissionStatus: (PermissionStatus) -> Unit
) {
    val permissionState = rememberPermissionState(permission)

    if (permissionState.status.isGranted) {
        onPermissionStatus(permissionState.status)
    }
    else if (permissionState.status.shouldShowRationale)
    {
        if (requiredPermission) {
            RequiredPermissionDialog(permission)
        } else {
            OptionalPermissionDialog(
                permission,
                dismissCallback = {
                    onPermissionStatus(permissionState.status)
                }
            )
        }
    }
    else {
       SideEffect {
            permissionState.launchPermissionRequest()
        }
    }
 }

@Composable
fun RequiredPermissionDialog (permission: String) {
    val context = LocalContext.current
    val permissionLabel = stringResource(
        context.packageManager.getPermissionInfo(permission, 0).labelRes
    )

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Permission Required!") },
        text = { Text(text = permissionLabel) },
        confirmButton = {
            Button(onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                startActivity(context, intent, null)
            }) {
                Text(text = "Go to settings")
            }
        },
    )
}

@Composable
fun OptionalPermissionDialog (permission: String, dismissCallback: () -> Unit) {
    val context = LocalContext.current
    val permissionLabel = stringResource(
        context.packageManager.getPermissionInfo(permission, 0).labelRes
    )

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Permission Required!") },
        text = { Text(text = permissionLabel) },
        confirmButton = {
            Button(onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                startActivity(context, intent, null)
            }) {
                Text(text = "Go to settings")
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
