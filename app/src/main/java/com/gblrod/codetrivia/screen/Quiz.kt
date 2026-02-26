package com.gblrod.codetrivia.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gblrod.codetrivia.ui.theme.CodeTriviaTheme

@Composable
fun Quiz(modifier: Modifier = Modifier) {
    var currentQuestion by remember { mutableIntStateOf(0) }
    val question = questions[currentQuestion]
    var optionSelected by remember { mutableIntStateOf(-1) }
    var answered by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(24.dp)
    ) {
        Text(
            text = "Questão ${currentQuestion + 1} de ${questions.size}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = question.text,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        question.options.forEachIndexed { index, option ->
            val backgroundResult = when{
                !answered -> Color.Transparent
                index == question.indexCorrect -> Color.Green
                index == optionSelected && index != question.indexCorrect -> Color.Red
                else -> Color.Transparent
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable(enabled = !answered) {
                        answered = true
                        optionSelected = index
                        if (index == question.indexCorrect) {
                            score++
                        }
                    }
                    .border(
                        width = 2.dp,
                        color = backgroundResult,
                        shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    text = option,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (answered) {
            if (currentQuestion < questions.size -1) {
                Button(
                    onClick = {
                        currentQuestion++
                        answered = false
                        optionSelected = -1
                    }
                ) {
                    Text(text = "Próxima")
                }
            } else {
                Button(onClick = {
                    showResult = true
                }) {
                    Text(text = "Ver Resultado")
                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        if (showResult) {
            Text(
                text = "Você acertou $score de ${questions.size} questões",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun QuizPreview() {
    CodeTriviaTheme {
        Quiz()
    }
}
*/