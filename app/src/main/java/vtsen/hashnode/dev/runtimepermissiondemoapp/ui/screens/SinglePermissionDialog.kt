package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermissionDialog(
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




