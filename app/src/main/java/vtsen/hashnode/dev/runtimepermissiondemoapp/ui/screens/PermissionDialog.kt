package vtsen.hashnode.dev.runtimepermissiondemoapp.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionDialog(
    permissionStr: String,
    shouldShowRequestPermissionRationale: (String) -> Boolean
)
{

    var isPermissionGranted by remember { mutableStateOf(true)}
    var shouldLaunchPermission by remember { mutableStateOf(true)}
    var showAlertDialog by remember { mutableStateOf(false)}


    val permissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            Log.d("vtsen", "launch() callback - isGranted: $isGranted")
            shouldLaunchPermission = false
            if(!isGranted) {
                showAlertDialog = true
            }
        }
    )

    if(shouldLaunchPermission) {
        SideEffect {
            Log.d("vtsen", "permissionResultLauncher.launch()")
            permissionResultLauncher.launch(
                permissionStr
            )
        }
    }

    if(showAlertDialog) {
        AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Alert Dialog Title") },
                text = { Text(text = "Alert Dialog Message") },
                confirmButton = {
                    Button(onClick = {
                        showAlertDialog = false
                        shouldLaunchPermission = true
                    }) {
                        Text(text = "OK")
                    }
                },
                //dismissButton = { Button(onClick = { }) { Text(text = "Cancel") } }
            )
    }

//    if(!isPermissionGranted) {
//
//        if(shouldShowRequestPermissionRationale(permissionStr)) {
//            Log.d("vtsen", "shouldShowRequestPermissionRationale: true")
//        } else {
//            Log.d("vtsen", "shouldShowRequestPermissionRationale: false")
//            //Log.d("vtsen", "shouldLaunchPermission = true")
//            AlertDialog(
//                onDismissRequest = { },
//                title = { Text(text = "Alert Dialog Title") },
//                text = { Text(text = "Alert Dialog Message") },
//                confirmButton = { Button(onClick = { }) { Text(text = "OK") } },
//                dismissButton = { Button(onClick = { }) { Text(text = "Cancel") } }
//            )
//        }
//    }

}

//Note: this doesn't work very well
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PermissionDialogFromAccompanish(
    permissionStr: String,
    )
{
    val permissionState = rememberPermissionState(permissionStr)

    if (permissionState.status.isGranted) {
        // do nothing
    }
    else if (permissionState.status.shouldShowRationale)
    {
        AlertDialog(onDismissRequest = { /*TODO*/ }) {

        }
    }
    else {
        SideEffect {
            permissionState.launchPermissionRequest()
        }
    }
 }