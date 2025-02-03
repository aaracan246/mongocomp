package com.example.mongocomp.controller

import com.example.mongocomp.error.exception.NotFoundException
import com.example.mongocomp.model.Noticia
import com.example.mongocomp.repository.NoticiasRepository
import com.example.mongocomp.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Not
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*

@RestController
class NoticiaController{

    @Autowired
    private lateinit var noticiasRepository: NoticiasRepository

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @PostMapping("/noticia")
    fun postNoticia(
        @RequestBody newNoticia: Noticia
    ): Noticia{

        val usuario = usuarioRepository.findById(newNoticia.usuario).orElseThrow() { NotFoundException("Parece que aún no hay noticias.") }

        if (!usuario.estado){ throw IllegalStateException("El usuario ${usuario.nick} está baneado.")}

        return noticiasRepository.insert(newNoticia)
    }

    @GetMapping("/noticias")
    fun getAllNoticias(): List<Noticia>{
        val noticias = noticiasRepository.findAll()

        if (noticias.isEmpty()){ throw NotFoundException("Parece que aún no hay noticias.") }

        return noticias
    }

    @GetMapping("/{id}")
    fun getNoticiaById(
        @PathVariable id: String
    ): Noticia{
        return noticiasRepository.findById(id).orElseThrow{ NotFoundException("No se ha encontrado una noticia con ese ID. ID: $id.")}}



    @PutMapping("/{id}")
    fun updateNoticia(
        @PathVariable id: String, @RequestBody nuevaNoticia: Map<String, String>
    ): Noticia{
        val noticiaExisting = noticiasRepository.findById(id).orElseThrow{ NotFoundException("No se ha encontrado una noticia con ese ID. ID: $id.")}

        val noticiaNew = noticiaExisting.copy(
            titulo = nuevaNoticia["titulo"] ?: noticiaExisting.titulo,
            cuerpo = nuevaNoticia["cuerpo"] ?: noticiaExisting.cuerpo,
            usuario = nuevaNoticia["usuario"] ?: noticiaExisting.usuario
        )

        noticiasRepository.save(noticiaNew) // cambiar esto al service si me da tiempo ******

        return noticiaNew
    }

    @DeleteMapping("/{id}")
    fun deleteNoticiaById(
        @PathVariable id: String
    ): String{
        if (noticiasRepository.existsById(id)){
            noticiasRepository.deleteById(id)
            return "Noticia eliminada con éxito."
        }
        else{ throw NotFoundException("No se ha encontrado una noticia con ese ID. ID: $id.") }
    }
}