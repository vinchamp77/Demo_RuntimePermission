package vtsen.hashnode.dev.runtimepermissiondemoapp.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions.RequiredMultiplePermissionScreen
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.theme.RuntimePermissionDemoAppTheme

class RequiredMultiplePermissionsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            RuntimePermissionDemoAppTheme {

                val permissions = mutableListOf(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
                ).toList()

                RequiredMultiplePermissionScreen(permissions)
            }
        }
    }
}
