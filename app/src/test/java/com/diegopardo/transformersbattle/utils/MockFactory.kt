package com.diegopardo.transformersbattle.utils

import com.diegopardo.transformersbattle.model.dto.TransformerDTO
import com.diegopardo.transformersbattle.model.dto.Transformers
import retrofit2.Response

fun getTransformersResponse(): Response<Transformers> {
    var transformer1 =
        TransformerDTO("1", "Transformer 1", "A", 1, 2, 3, 4, 5, 6, 7, 8, "http://icon.url")
    var transformer2 =
        TransformerDTO("2", "Transformer 2", "D", 1, 2, 3, 4, 5, 6, 7, 8, "http://icon.url")
    var transformer3 =
        TransformerDTO("3", "Transformer 3", "A", 1, 2, 3, 4, 5, 6, 7, 8, "http://icon.url")
    var transformer4 =
        TransformerDTO("4", "Transformer 4", "D", 1, 2, 3, 4, 5, 6, 7, 8, "http://icon.url")
    var transformer5 =
        TransformerDTO("5", "Transformer 5", "A", 1, 2, 3, 4, 5, 6, 7, 8, "http://icon.url")
    var transformers = mutableListOf<TransformerDTO>()
    transformers.add(transformer1)
    transformers.add(transformer2)
    transformers.add(transformer3)
    transformers.add(transformer4)
    transformers.add(transformer5)
    var transformersDTO = Transformers(transformers)
    return Response.success(transformersDTO)
}

fun getTransformerDTOToCreate(): TransformerDTO {
    return TransformerDTO(null, "Transformer 1", "A", 1, 2, 3, 4, 5, 6, 7, 8, null)
}

private fun getCreatedTransformerDTO(): TransformerDTO { // With value for id and team_icon
    return TransformerDTO("1", "Transformer 1", "A", 1, 2, 3, 4, 5, 6, 7, 8, "http://icon.url")
}

fun createdTransformerResponse(): Response<TransformerDTO> {
    val transformerDTO = return Response.success(201, getCreatedTransformerDTO())
}

fun getTransformerDTOToUpdate(): TransformerDTO {
    return getCreatedTransformerDTO()
}

fun updatedTransformerResponse(): Response<TransformerDTO> {
    return Response.success(200, getCreatedTransformerDTO())
}