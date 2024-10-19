package com.lexterlab.fusionfallapi.fusionfall.repository

import com.lexterlab.fusionfallapi.fusionfall.model.Cartoon
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CartoonRepository: JpaRepository<Cartoon, UUID>