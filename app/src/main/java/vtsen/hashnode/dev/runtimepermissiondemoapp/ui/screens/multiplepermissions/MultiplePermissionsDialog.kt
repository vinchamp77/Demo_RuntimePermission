package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermissionsDialog(
    permissions: List<String>,
    requiredPermission: Boolean,
    onPermissionStatus: (List<PermissionState>) -> Unit
) {
    val permissionState = rememberMultiplePermissionsState(permissions)

    if (permissionState.allPermissionsGranted) {
        onPermissionStatus(permissionState.permissions)

    } else if (permissionState.shouldShowRationale) {
        if (requiredPermission) {
            RequiredPermissionsDialog(
                permissions,
            )

        } else {
            OptionalPermissionsDialog(
                permissions,
                dismissCallback = {
                    onPermissionStatus(permissionState.permissions)
                }
            )
        }
    } else {
        SideEffect {
            //Note: Accompanist limitation - user click back button doesn't update
            //the permission state
            permissionState.launchMultiplePermissionRequest()
        }
    }
 }
