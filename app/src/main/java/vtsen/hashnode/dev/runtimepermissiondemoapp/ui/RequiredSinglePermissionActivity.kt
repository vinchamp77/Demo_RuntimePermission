package vtsen.hashnode.dev.runtimepermissiondemoapp.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.singlepermission.RequiredSinglePermissionScreen
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.theme.RuntimePermissionDemoAppTheme

class RequiredSinglePermissionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            RuntimePermissionDemoAppTheme {

                RequiredSinglePermissionScreen(Manifest.permission.CALL_PHONE)
            }
        }
    }
}
