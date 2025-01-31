package com.example.mongocomp.controller

import com.example.mongocomp.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class UsuarioController {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository


}