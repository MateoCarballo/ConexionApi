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


@Composable
fun HomeScreen(
    homeScreenVM: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
) {
    val state = homeScreenVM.homeViewState.collectAsState()
    if (state.value.loading){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Loading...")
            Button(
                onClick = {homeScreenVM.loadQuestions(10)}
            ) {
                Text("Cargar preguntas")
            }
        }
    }else{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = state.value.questionsList[state.value.questionIndex].title
            )

            state.value.questionsList[state.value.questionIndex].options.forEach {
                Text(
                    text = it,
                )
            }
            Button(
                onClick = {homeScreenVM.nextQuestion()}
            ) {
                Text("Siguiente pregunta")
            }
        }
    }
}
@Preview()
@Composable
fun PreviewHomeScreen(){
    HomeScreen()
}