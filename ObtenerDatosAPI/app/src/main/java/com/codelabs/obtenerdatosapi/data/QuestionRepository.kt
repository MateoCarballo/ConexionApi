package com.codelabs.obtenerdatosapi.data

import android.util.Log
import com.codelabs.obtenerdatosapi.network.TriviaApiService
import com.codelabs.obtenerdatosapi.ui.model.QuestionApi

interface QuestionRepository{
    suspend fun getQuestions(quantity: Int): List<QuestionApi>
}

class NetWorkQuestionRepository(
    private val triviaApiService: TriviaApiService
) : QuestionRepository{
    override suspend fun getQuestions(quantity: Int): List<QuestionApi> {
        val response = triviaApiService.getApiQuestions(
            amount = quantity
        )
        return if(response.isSuccessful){
            response.body()?.results ?: emptyList()
        }else emptyList()
    }

}