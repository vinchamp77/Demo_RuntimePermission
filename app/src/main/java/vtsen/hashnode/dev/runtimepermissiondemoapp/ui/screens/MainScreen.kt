package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.MultiplePermissionsActivity
import vtsen.hashnode.dev.runtimepermissiondemoapp.ui.SinglePermissionActivity

@Composable
fun MainScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            context.startActivity(
                Intent(context, SinglePermissionActivity::class.java)
            )
        }) {
            Text(text = "Single Permission")
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            context.startActivity(
                Intent(context, MultiplePermissionsActivity::class.java)
            )
        }) {
            Text(text = "Multiple Permission")
        }
    }
}
