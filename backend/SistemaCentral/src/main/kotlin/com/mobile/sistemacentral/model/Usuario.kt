package com.mobile.sistemacentral.model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String = "",
    val senha: String = ""
)