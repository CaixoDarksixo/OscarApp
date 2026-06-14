package com.mobile.sistemacentral.model

import jakarta.persistence.*

@Entity
@Table(name = "votos")
class Voto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    val usuario: Usuario? = null,

    @Column(name = "filme_id")
    val filmeId: Int = 0,

    @Column(name = "diretor_id")
    val diretorId: Int = 0
)