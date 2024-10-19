package com.lexterlab.fusionfallapi.fusionfall.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "cartoons")
class Cartoon(
    @Id
    val id: UUID? = UUID.randomUUID(),
    @Column(nullable = false, unique = true)
    val name: String?,
) {
    override fun toString(): String {
        return "Cartoon(id=$id, name='$name')"
    }
}