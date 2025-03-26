package com.codelabs.obtenerdatosapi.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelabs.obtenerdatosapi.model.Question


@Composable
fun HomeScreen(
    homeScreenVM: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
) {
    val state = homeScreenVM.homeViewState.collectAsState()
    if (state.value.loading) {
        LoadingScreen(
            getQuestions = { homeScreenVM.loadQuestions(10) }
        )
    } else {
        QuestionCard(
            nextQuestion = { homeScreenVM.nextQuestion() },
            question = state.value.questionsList[state.value.questionIndex],
        )
    }
}

@Composable
fun LoadingScreen(
    getQuestions: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Loading...")
        Button(
            onClick = getQuestions
        ) {
            Text("Cargar preguntas")
        }
    }
}

@Composable
fun QuestionCard(
    nextQuestion: () -> Unit,
    question: Question,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = question.title,
            fontSize = 20.sp,
            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
        )
        Spacer(Modifier.padding(8.dp))
        question.options.forEach {
            Button(
                onClick = {
                    // Accion al contestar
                },
            ){
                Text(
                    text = it
                )
            }
            Spacer(Modifier.padding(8.dp))
        }
        Button(
            onClick = { nextQuestion() }
        ) {
            Text("Siguiente pregunta")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRespuesta() {
    QuestionCard({},Question("Titulo", listOf("Respuesta 1","Respuesta 2","Respuesta 3","Respuesta 4"),"Respuesta 1"))
}
