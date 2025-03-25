package com.lbodaszsservidor.modeladojpa.repository;

import com.lbodaszsservidor.modeladojpa.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, UUID> {

    // Solo empleados activos
    @Query("SELECT e FROM Empleado e WHERE e.fechaEliminacion IS NULL")
    List<Empleado> findEmpleadosActivos();
}
