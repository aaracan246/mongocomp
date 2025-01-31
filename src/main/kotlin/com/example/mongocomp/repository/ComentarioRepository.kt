package com.example.mongocomp.repository

import com.example.mongocomp.model.Comentario
import org.springframework.data.mongodb.repository.MongoRepository

interface ComentarioRepository: MongoRepository<Comentario, String> {
}