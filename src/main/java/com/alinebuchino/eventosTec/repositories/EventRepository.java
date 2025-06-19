package com.alinebuchino.eventosTec.repositories;

import com.alinebuchino.eventosTec.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
