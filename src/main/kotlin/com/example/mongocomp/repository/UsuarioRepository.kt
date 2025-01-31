package com.example.mongocomp.repository

import com.example.mongocomp.model.Usuario
import org.springframework.data.mongodb.repository.MongoRepository

interface UsuarioRepository: MongoRepository<Usuario, String> {
}