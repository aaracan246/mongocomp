package com.example.mongocomp.controller

import com.example.mongocomp.error.exception.NotFoundException
import com.example.mongocomp.model.Usuario
import com.example.mongocomp.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UsuarioController {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @PostMapping("/usuario")
    fun insertUsuario(@RequestBody newUsuario: Usuario): Usuario {
        return usuarioRepository.insert(newUsuario)
    }

    @GetMapping("/usuarios")
    fun getAllUsers(): List<Usuario> {
        val usuarios = usuarioRepository.findAll()
        if (usuarios.isEmpty()) {
            throw NotFoundException("Parece que no se han encontrado usuarios.")
        }
        return usuarios
    }

    @GetMapping("/usuario/{id}")
    fun getUsuarioById(@PathVariable id: String): Usuario {
        return usuarioRepository.findById(id).orElseThrow {
            NotFoundException("No se ha encontrado un usuario con ese ID. ID: $id.")
        }
    }


    @PutMapping("/usuario/{id}")
    fun updateUsuario(@PathVariable id: String, @RequestBody updatedUsuario: Usuario): Usuario {
        val usuarioExisting = usuarioRepository.findById(id).orElseThrow {
            NotFoundException("No se ha encontrado un usuario con ese ID. ID: $id.")
        }

        val usuarioUpdated = usuarioExisting.copy(
            nick = updatedUsuario.nick,
            estado = updatedUsuario.estado,
            direccion = updatedUsuario.direccion,
            tlfn = updatedUsuario.tlfn
        )

        return usuarioRepository.save(usuarioUpdated)
    }


    @DeleteMapping("/usuario/{id}")
    fun deleteUsuarioById(@PathVariable id: String): String {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
            return "Usuario eliminado con éxito."
        } else {
            throw NotFoundException("No se ha encontrado un usuario con ese ID. ID: $id.")
        }
    }
}