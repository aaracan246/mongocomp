package com.example.mongocomp.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "collDireccion")
data class Direccion(
    @Field
    val calle: String,
    @Field
    val num: Int,
    @Field
    val cp: String,
    @Field
    val ciudad: String
)
