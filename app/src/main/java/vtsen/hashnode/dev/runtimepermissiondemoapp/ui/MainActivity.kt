package vtsen.hashnode.dev.runtimepermissiondemoapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens.MainScreen
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.theme.RuntimePermissionDemoAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setupSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            RuntimePermissionDemoAppTheme {
                MainScreen(::shouldShowRequestPermissionRationale)
            }
        }
    }

    override fun shouldShowRequestPermissionRationale(permission: String) : Boolean
    {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.shouldShowRequestPermissionRationale(permission)
        } else {
            false
        }
    }

    private fun setupSplashScreen() {
        var keepSplashScreenOn = true
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                delay(2000)
                keepSplashScreenOn = false
            }
        }

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreenOn
        }
    }
}
