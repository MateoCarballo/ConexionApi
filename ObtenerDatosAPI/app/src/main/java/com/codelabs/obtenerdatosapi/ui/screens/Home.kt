package com.codelabs.obtenerdatosapi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    homeScreenVM: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
) {
    val state = homeScreenVM.homeViewState.collectAsState()
    val currentQuestion = state.value.questionsList.getOrNull(state.value.questionSelected)
    // Si hay una pregunta, la mostramos
    currentQuestion?.let { question ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .padding(all = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = question.title,
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Mostrar las opciones de la pregunta
                    question.options.forEach { answer ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    homeScreenVM.selectAnswer(answer)  // Actualizamos la respuesta seleccionada
                                }
                        ) {
                            RadioButton(
                                selected = (answer == state.value.selectedAnswer),  // Compara la opción con la respuesta seleccionada
                                onClick = {
                                    homeScreenVM.selectAnswer(answer)  // Actualizamos la respuesta seleccionada
                                }
                            )
                            Text(text = answer, fontSize = 16.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para pasar a la siguiente pregunta
                    Text(
                        text = "Siguiente pregunta",
                        fontSize = 16.sp,
                        color = Color.Blue,
                        modifier = Modifier.clickable {
                            homeScreenVM.nextQuestion()  // Pasamos a la siguiente pregunta
                        }
                    )
                }
            }
        }
    } ?: run {
        // Si no hay preguntas disponibles, mostrar un mensaje
        Text(text = "No hay preguntas disponibles.", fontSize = 18.sp, color = Color.Red)
    }
}