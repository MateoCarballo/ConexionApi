package com.codelabs.obtenerdatosapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelabs.obtenerdatosapi.ui.model.Question


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
        //state.value.questionsList[state.value.questionIndex]
        text = question.title
    )

    question.options.forEach {
        Text(
            text = it,
        )
    }
    Button(
        onClick = { nextQuestion() }
    ) {
        Text("Siguiente pregunta")
    }
}
}

@Preview()
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}