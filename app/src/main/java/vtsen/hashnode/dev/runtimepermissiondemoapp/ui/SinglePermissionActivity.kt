package vtsen.hashnode.dev.runtimepermissiondemoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.singlepermission.SinglePermissionScreen
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.theme.RuntimePermissionDemoAppTheme

class SinglePermissionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            RuntimePermissionDemoAppTheme {
                SinglePermissionScreen()
            }
        }
    }
}
