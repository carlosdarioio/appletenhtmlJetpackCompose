package com.example.appletenhtml
// MainActivity.kt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.appletenhtml.navigation.AppNavigation
import com.example.appletenhtml.ui.theme.AppletenhtmlTheme
import com.example.appletenhtml.views.EjemploSuma

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppletenhtmlTheme {
                AppNavigation()

            }
        }
    }
}



@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("YAA WEY...")
        Button(onClick = { navController.navigate("suma") }) { Text("1. Ejemplo de suma") }
        Button(onClick = { navController.navigate("conversion") }) { Text("2. Conversión entre tipos") }
        Button(onClick = { navController.navigate("edad") }) { Text("3. Calcular edad") }
        /*Button(onClick = { navController.navigate("bisiesto") }) { Text("4. Año bisiesto") }
        Button(onClick = { navController.navigate("ciclo_for") }) { Text("5. Ejemplo ciclo for") }
        Button(onClick = { navController.navigate("par_impar") }) { Text("6. Número par o impar") }
        Button(onClick = { navController.navigate("login") }) { Text("7. Login auth") }
        Button(onClick = { navController.navigate("validacion") }) { Text("8. Validar auth en vista") }
        Button(onClick = { navController.navigate("crud") }) { Text("9. CRUD con API") }*/
    }
}

