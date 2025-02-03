package com.example.mongocomp.controller

import com.example.mongocomp.error.exception.NotFoundException
import com.example.mongocomp.model.Comentario
import com.example.mongocomp.model.Noticia
import com.example.mongocomp.repository.ComentarioRepository
import com.example.mongocomp.repository.NoticiasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class ComentarioController {

    @Autowired
    private lateinit var comentarioRepository: ComentarioRepository

    @Autowired
    private lateinit var noticiasRepository: NoticiasRepository

    @PostMapping("/noticia/{id}/comentario")
    fun insertComentario(
        @PathVariable id: String,
        @RequestBody comentario: Comentario
    ): Noticia {
        val noticia = noticiasRepository.findById(id).orElseThrow {
            NotFoundException("No se ha encontrado una noticia con ese ID. ID: $id.")
        }

        val updatedComentarios = noticia.comentarios + comentario
        val updatedNoticia = noticia.copy(comentarios = updatedComentarios)

        return noticiasRepository.save(updatedNoticia)
    }

    @GetMapping("/noticia/{id}/comentarios")
    fun getAllComentarios(@PathVariable id: String): List<Comentario> {
        val noticia = noticiasRepository.findById(id).orElseThrow {
            NotFoundException("No se ha encontrado una noticia con ese ID. ID: $id.")
        }

        return noticia.comentarios
    }


    @DeleteMapping("/noticia/{id}/comentario/{comentarioId}")
    fun deleteComentario(
        @PathVariable id: String,
        @PathVariable comentarioId: String
    ): Noticia {
        val noticia = noticiasRepository.findById(id).orElseThrow {
            NotFoundException("No se ha encontrado una noticia con ese ID. ID: $id.")
        }

        val updatedComentarios = noticia.comentarios.filterNot { it._id?.toString() == comentarioId } // Filtra dejando fuera lo que cumpla el filtro, manteniendo el resto de comentarios
        val updatedNoticia = noticia.copy(comentarios = updatedComentarios)

        return noticiasRepository.save(updatedNoticia)
    }
}