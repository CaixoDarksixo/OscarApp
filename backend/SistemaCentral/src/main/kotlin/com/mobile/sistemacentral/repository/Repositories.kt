package com.mobile.sistemacentral.repository

import com.mobile.sistemacentral.model.Token
import com.mobile.sistemacentral.model.Usuario
import com.mobile.sistemacentral.model.Voto
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByUsername(username: String): Usuario?
}

interface TokenRepository : JpaRepository<Token, Long> {
    fun findByUsuarioId(usuarioId: Long): Token?
    fun findByValor(valor: Int): Token?
}

interface VotoRepository : JpaRepository<Voto, Long> {
    fun existsByUsuarioId(usuarioId: Long): Boolean
}