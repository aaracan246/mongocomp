package com.example.mongocomp.repository

import com.example.mongocomp.model.Noticia
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoticiasRepository: MongoRepository<Noticia, String> {

}