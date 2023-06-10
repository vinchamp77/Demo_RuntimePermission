package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat

@Composable
fun RequiredRationalPermissionsDialog (
    permissions: List<String>
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
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                ContextCompat.startActivity(context, intent, null)
            }) {
                Text(text = "Go to settings")
            }
        },
    )
}