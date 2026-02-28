package com.gblrod.codetrivia.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gblrod.codetrivia.model.questions
import com.gblrod.codetrivia.ui.theme.OptionBackground
import com.gblrod.codetrivia.ui.theme.OptionSelectedBackground
import com.gblrod.codetrivia.ui.theme.QuizCardBackground
import com.gblrod.codetrivia.R
import com.gblrod.codetrivia.ui.theme.ButtonNext
import com.gblrod.codetrivia.ui.theme.ButtonRestart
import com.gblrod.codetrivia.ui.theme.ButtonResult
import com.gblrod.codetrivia.ui.theme.CodeTriviaTheme

@Composable
fun Quiz(modifier: Modifier = Modifier) {
    var currentQuestion by remember { mutableIntStateOf(0) }
    val question = questions[currentQuestion]
    var optionSelected by remember { mutableIntStateOf(-1) }
    var answered by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background do Aplicativo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )
        Column(
            modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Questão ${currentQuestion + 1} de ${questions.size}",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(
                        width = 2.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = QuizCardBackground),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = question.text,
                        modifier = Modifier.padding(horizontal = 20.dp),
                        fontSize = 17.sp,
                        lineHeight = 20.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    question.options.forEachIndexed { index, option ->
                        val backgroundResult = when {
                            !answered -> Color.Transparent
                            index == question.indexCorrect -> Color.Green
                            index == optionSelected && index != question.indexCorrect -> Color.Red
                            else -> OptionSelectedBackground
                        }
                        Card(
                            modifier = Modifier
                                .width(400.dp)
                                .padding(vertical = 4.dp, horizontal = 20.dp)
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
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = OptionBackground),
                        )
                        {
                            Text(
                                text = option,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (answered) {
                if (currentQuestion < questions.size - 1) {
                    Button(
                        onClick = {
                            currentQuestion++
                            answered = false
                            optionSelected = -1
                        },
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonNext),
                        modifier = Modifier.fillMaxWidth(0.7f)
                    )
                    {
                        Text(
                            text = "Próxima",
                            color = Color.White,
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            if (!showResult) {
                                showResult = true
                            } else {
                                questions = questions.shuffled()
                                currentQuestion = 0
                                score = 0
                                optionSelected = -1
                                answered = false
                                showResult = false
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = if (!showResult) ButtonResult else ButtonRestart),
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        if (!showResult) {
                            Text(
                                text = "Ver Resultado",
                                color = Color.White,
                            )
                        } else {
                            Text(
                                text = "Recomeçar",
                                color = Color.White,
                            )
                        }
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
}

//@Preview(showBackground = true)
//@Composable
//private fun QuizPreview() {
//    CodeTriviaTheme {
//        Quiz()
//    }
//}
