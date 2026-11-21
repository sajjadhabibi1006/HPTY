package com.devkit.hbty

import android.R.attr.label
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devkit.hbty.ui.theme.HBTYTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HBTYTheme {
                userinfo()
            }
        }
    }
}

@Composable
fun userinfo(){
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    var showRes by remember { mutableStateOf(false)}
    var showFnl by remember { mutableStateOf(0)}

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        TextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("name")}
        )
        TextField(
            value = price,
            onValueChange = {price = it},
            label = {Text("price")}
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))

        Button(onClick = {

            val amount = price.toIntOrNull() ?:0

            if (amount < 500 && amount >= 1){
                Toast.makeText(context, "مبلغ بسیار پایین است.",
                    Toast.LENGTH_SHORT).show()
            }

            else if (amount < 1000 && amount >= 500){
               val sum = (amount / 10)
                showFnl = amount - sum
                showRes = true
            }

            else if(amount >= 1000){
                val sum = amount / 5
                showFnl = amount - sum
                showRes = true
            }

            else{
                showFnl = amount
                showRes = true

            }
        },) {
            Text(text = "Meadeh")
        }
        if (showRes){
            Text("نام: $name")
            Text("قیمت نهایی: $showFnl")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HBTYTheme {
        userinfo()
    }
}