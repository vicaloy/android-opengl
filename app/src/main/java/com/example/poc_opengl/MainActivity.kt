package com.example.poc_opengl

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.poc_opengl.ui.theme.PocopenglTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocopenglTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                context.startActivity(Intent(context, OpenGLES20Activity::class.java).apply {
                    val mBundle = Bundle()
                    mBundle.putInt("shape", 0)
                    putExtras(mBundle)
                })
            }) {
            Text(text = "Square")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                context.startActivity(Intent(context, OpenGLES20Activity::class.java).apply {
                    val mBundle = Bundle()
                    mBundle.putInt("shape", 1)
                    putExtras(mBundle)
                })
            }) {
            Text(text = "Triangle simple")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                context.startActivity(Intent(context, OpenGLES20Activity::class.java).apply {
                    val mBundle = Bundle()
                    mBundle.putInt("shape", 2)
                    putExtras(mBundle)
                })
            }) {
            Text(text = "Triangle rotate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                context.startActivity(Intent(context, OpenGLES20Activity::class.java).apply {
                    val mBundle = Bundle()
                    mBundle.putInt("shape", 3)
                    putExtras(mBundle)
                })
            }) {
            Text(text = "Triangle colour")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PocopenglTheme {
        Main()
    }
}