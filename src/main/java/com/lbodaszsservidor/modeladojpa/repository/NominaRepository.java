package com.lbodaszsservidor.modeladojpa.repository;

import com.lbodaszsservidor.modeladojpa.entity.Empleado;
import com.lbodaszsservidor.modeladojpa.entity.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, UUID> {

    boolean existsByEmpleadoAndMesAndAnio(Empleado juan, String marzo, int i);
}
