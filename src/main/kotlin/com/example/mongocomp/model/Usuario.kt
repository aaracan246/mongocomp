package com.example.mongocomp.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "collUsuarios")
data class Usuario(
    @Id
    val _id: ObjectId?,
    @Field
    val nick: String,
    @Field
    val estado: Boolean,
    @Field
    val direccion: Direccion,
    @Field
    val tlfn: String
)
