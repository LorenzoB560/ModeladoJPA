package com.lbodaszsservidor.modeladojpa.repository;

import com.lbodaszsservidor.modeladojpa.entity.Becario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BecarioRepository extends JpaRepository<Becario, UUID> {
}
