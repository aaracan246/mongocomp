package com.example.mongocomp.controller

import com.example.mongocomp.repository.ComentarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
class ComentarioController {

    @Autowired
    private lateinit var comentarioRepository: ComentarioRepository
}