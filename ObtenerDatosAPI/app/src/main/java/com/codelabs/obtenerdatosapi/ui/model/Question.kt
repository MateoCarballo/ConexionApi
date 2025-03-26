package com.codelabs.obtenerdatosapi.ui.model

data class Question (
    val rawtitle: String,
    val rawOptions: List<String>,
    val rawCorrectAnswer: String,
    )
{
    val title: String
        get() = rawtitle.decodeHtml()

    val options: List<String>
        get() = rawOptions.map { it.decodeHtml() }

    val correctAnswer: String
        get() = rawCorrectAnswer.decodeHtml()

    fun validateAnswer(answer: String): Boolean {
        return answer == correctAnswer
    }

    private fun String.decodeHtml(): String {
        return android.text.Html.fromHtml(this, android.text.Html.FROM_HTML_MODE_LEGACY).toString()
    }
}