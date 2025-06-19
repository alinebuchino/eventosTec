package com.alinebuchino.eventosTec.repositories;

import com.alinebuchino.eventosTec.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
