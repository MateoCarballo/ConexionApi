package com.codelabs.obtenerdatosapi.ui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionApi(
    @SerialName("question")
    val title: String,
    @SerialName("correct_answer")
    val correct_answer: String,
    @SerialName("incorrect_answer")
    val answers: List<String>
) {
    fun toQuestion(): Question {
        val options = answers.toMutableList()
        options.add(correct_answer)
        return Question(title,options.shuffled(),correct_answer)
    }
}