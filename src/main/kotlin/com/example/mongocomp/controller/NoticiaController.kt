package com.example.mongocomp.controller

import com.example.mongocomp.model.Noticia
import com.example.mongocomp.repository.NoticiasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*

@RestController
class NoticiaController{

    @Autowired
    private lateinit var noticiasRepository: NoticiasRepository


    @GetMapping("/noticia")
    fun getNoticia(): String{
        val noticia = Noticia(null, "Sale al mercado la nueva HelloKitty", "Illo vaya locura no??? la nueva jelougirlkittygirl;)", Date.from(Instant.now()), listOf("Hello", "Kitty"), "idClientePrueba")

        noticiasRepository.insert(noticia)

        return "¡Noticia insertada con éxito!"
    }

    @GetMapping
    fun getAllNoticias(){
        val noticias = noticiasRepository.findAll()

        if (noticias.isEmpty()){
            throw
        }

    }
}