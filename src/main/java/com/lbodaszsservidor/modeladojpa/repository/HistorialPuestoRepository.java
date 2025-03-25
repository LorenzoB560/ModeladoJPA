package com.lbodaszsservidor.modeladojpa.repository;

import com.lbodaszsservidor.modeladojpa.entity.HistorialPuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistorialPuestoRepository extends JpaRepository<HistorialPuesto, UUID> {
}
