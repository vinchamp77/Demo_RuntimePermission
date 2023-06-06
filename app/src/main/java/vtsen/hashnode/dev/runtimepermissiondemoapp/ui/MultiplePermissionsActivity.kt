package vtsen.hashnode.dev.runtimepermissiondemoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.MultiplePermissionsScreen
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.theme.RuntimePermissionDemoAppTheme

class MultiplePermissionsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            RuntimePermissionDemoAppTheme {
                MultiplePermissionsScreen()
            }
        }
    }
}
