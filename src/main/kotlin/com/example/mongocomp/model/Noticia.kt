package com.example.mongocomp.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "collNoticias")
data class Noticia(
    @Id
    val _id: ObjectId?,
    @Field
    val titulo: String,
    @Field
    val cuerpo: String,
    @Field
    val fechaPub: Date,
    @Field
    val tags: List<String>,
    @Field
    val usuario: String
    )
