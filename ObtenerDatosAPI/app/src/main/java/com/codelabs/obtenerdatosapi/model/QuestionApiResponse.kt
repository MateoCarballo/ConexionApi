package com.codelabs.obtenerdatosapi.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionApiResponse(
    val response_code : Int,
    val results: List<QuestionApi>
)