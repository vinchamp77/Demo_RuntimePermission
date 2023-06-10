package vtsen.hashnode.dev.runtimepermissiondemoapp.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.multiplepermissions.OptionalMultiplePermissionScreen
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.theme.RuntimePermissionDemoAppTheme

class OptionalMultiplePermissionsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            RuntimePermissionDemoAppTheme {

                val permissions = mutableListOf(
                    Manifest.permission.CALL_PHONE,
                    //Manifest.permission.CAMERA,
                    //Manifest.permission.RECORD_AUDIO
                ).toList()

                OptionalMultiplePermissionScreen(permissions)
            }
        }
    }
}
