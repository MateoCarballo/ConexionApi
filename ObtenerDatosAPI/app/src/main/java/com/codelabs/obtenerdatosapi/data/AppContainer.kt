package com.codelabs.obtenerdatosapi.data

import com.codelabs.obtenerdatosapi.network.TriviaApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val questionRepository: QuestionRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://opentdb.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
    private val retrofitService: TriviaApiService by lazy { retrofit.create(TriviaApiService::class.java) }
    override val questionRepository: QuestionRepository by lazy { NetWorkQuestionRepository(retrofitService) }
}