package com.alinebuchino.eventosTec.repositories;

import com.alinebuchino.eventosTec.domain.Cupons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CupomRepository extends JpaRepository<Cupons, UUID> {
}
