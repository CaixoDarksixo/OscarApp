package com.mobile.sistemacentral.controller

import com.mobile.sistemacentral.dto.*
import com.mobile.sistemacentral.model.Token
import com.mobile.sistemacentral.model.Voto
import com.mobile.sistemacentral.repository.TokenRepository
import com.mobile.sistemacentral.repository.UsuarioRepository
import com.mobile.sistemacentral.repository.VotoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

@RestController
@RequestMapping("/api")
class OscarController(
    private val usuarioRepository: UsuarioRepository,
    private val tokenRepository: TokenRepository,
    private val votoRepository: VotoRepository
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val usuario = usuarioRepository.findByUsername(request.username)

        if (usuario == null || usuario.senha != request.senha) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MessageResponse("Usuário ou senha inválidos."))
        }

        val tokenValor = Random.nextInt(0, 101)

        var token = tokenRepository.findByUsuarioId(usuario.id)
        if (token != null) {
            token.valor = tokenValor
        } else {
            token = Token(usuario = usuario, valor = tokenValor)
        }
        tokenRepository.save(token)

        return ResponseEntity.ok(LoginResponse(token.valor, "Login realizado com sucesso!"))
    }

    @PostMapping("/voto")
    fun registrarVoto(@RequestBody request: VotoRequest): ResponseEntity<Any> {
        val tokenEntity = tokenRepository.findByValor(request.token)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MessageResponse("Token inválido ou não encontrado."))

        val usuarioLogado = tokenEntity.usuario!!

        if (votoRepository.existsByUsuarioId(usuarioLogado.id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageResponse("Este usuário já registrou um voto e não pode votar novamente."))
        }

        val novoVoto = Voto(
            usuario = usuarioLogado,
            filmeId = request.filmeId,
            diretorId = request.diretorId
        )
        votoRepository.save(novoVoto)

        return ResponseEntity.ok(MessageResponse("Voto registrado com sucesso para o Oscar!"))
    }
}