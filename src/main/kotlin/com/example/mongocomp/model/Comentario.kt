package com.example.mongocomp.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "collComentarios")
data class Comentario(
    @Id
    val _id: ObjectId,
    @Field
    val nick: String,
    @Field
    val noticia: Noticia
)
