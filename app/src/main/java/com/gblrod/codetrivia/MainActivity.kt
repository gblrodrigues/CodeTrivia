package com.gblrod.codetrivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gblrod.codetrivia.screen.Quiz
import com.gblrod.codetrivia.ui.theme.CodeTriviaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeTriviaTheme {
                Quiz()
            }
        }
    }
}