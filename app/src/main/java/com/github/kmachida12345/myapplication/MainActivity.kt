package com.github.kmachida12345.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kmachida12345.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")

                }
            }
        }


    }

}

@Composable
fun Greeting(name: String) {

    val patternElements = remember { mutableStateListOf<Long>(0, 0, 0, 0) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(

                onClick = { patternElements.add(0) }) {
                Icon(Icons.Filled.Add, contentDescription = "add pattern element")
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                itemsIndexed(patternElements) { index, item ->
                    Card {
                        TextField(
                            value = patternElements[index].toString(),
                            onValueChange = {
                                kotlin.runCatching {
                                    patternElements[index] = it.toLong()
                                }
                            },
                            label = { Text(text = if (index % 2 == 0) "休" else "鳴") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }

            val context = LocalContext.current
            Button(onClick = {

                val hoge = NotificationChannelGenerator()
                hoge.createNotificationChannel(
                    patternElements.toLongArray(),
                    context
                )
                hoge.sendNotification(context)
            }) {
                Text(text = "create channel and send")

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}