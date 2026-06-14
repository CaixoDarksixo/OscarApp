package com.mobile.sistemacentral.model

import jakarta.persistence.*

@Entity
@Table(name = "tokens")
class Token(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    val usuario: Usuario? = null,

    var valor: Int = 0
)