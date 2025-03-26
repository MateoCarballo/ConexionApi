package com.codelabs.obtenerdatosapi.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.codelabs.obtenerdatosapi.TriviaApplication
import com.codelabs.obtenerdatosapi.data.QuestionRepository
import com.codelabs.obtenerdatosapi.ui.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeScreenState(
    val questionsList: List<Question> = emptyList(),
    val questionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val loading: Boolean = true,
)

class HomeScreenViewModel(private val questionRepository: QuestionRepository) : ViewModel(){

    private val _homeViewState = MutableStateFlow(HomeScreenState())
    val homeViewState: StateFlow<HomeScreenState> = _homeViewState.asStateFlow()

    init {
        loadQuestions(10)
    }

    fun loadQuestions(quantity: Int) {
        viewModelScope.launch {
            val questions = questionRepository.getQuestions(quantity).map { it.toQuestion() }
            _homeViewState.update {
                it.copy(
                    questionsList = questions,
                )
            }
        }
    }

    fun selectAnswer(answer: String){
        _homeViewState.update {
            it.copy(
                selectedAnswer = answer
            )
        }
    }

    fun nextQuestion(){
        if (_homeViewState.value.questionIndex < (_homeViewState.value.questionsList.size -1)){
            _homeViewState.update {
                it.copy(
                    questionIndex = _homeViewState.value.questionIndex + 1
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TriviaApplication)
                val questionRepository = application.container.questionRepository
                HomeScreenViewModel(questionRepository)
            }
        }
    }
}

