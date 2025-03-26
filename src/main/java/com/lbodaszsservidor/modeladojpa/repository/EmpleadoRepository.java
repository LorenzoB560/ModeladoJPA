package com.lbodaszsservidor.modeladojpa.repository;

import com.lbodaszsservidor.modeladojpa.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, UUID> {

    // 1. Listado de empleados de un departamento
    List<Empleado> findByDepartamentoNombre(String nombre);

    // 2. Lista de empleados que hayan trabajado en la empresa en un periodo de tiempo
    @Query("SELECT e FROM Empleado e WHERE e.periodo.fechaInicio <= :fechaFin AND e.periodo.fechaFin >= :fechaInicio")
    List<Empleado> findEmpleadosByPeriodo(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    // 3. Listado de empleados cuyo salario actual esté en un rango de valores
    @Query("SELECT DISTINCT e FROM Empleado e " +
            "JOIN e.nominas n " +
            "JOIN n.lineaNominas ln " +
            "WHERE ln.salario BETWEEN :minSalario AND :maxSalario")
    List<Empleado> findEmpleadosBySalarioRango(@Param("minSalario") Double minSalario, @Param("maxSalario") Double maxSalario);

    // 4. Listado de empleados subordinados de un jefe
    List<Empleado> findByJefeId(UUID jefeId);

    // 5. Sueldo líquido de un empleado en un mes determinado
    @Query("SELECT SUM(ln.liquido) FROM LineaNomina ln " +
            "JOIN ln.nomina n " +
            "WHERE n.empleado.id = :empleadoId AND n.mes = :mes AND n.anio = :anio")
    Double findSueldoLiquido(@Param("empleadoId") UUID empleadoId, @Param("mes") String mes, @Param("anio") int anio);

    // 6. Sueldo bruto anual de un empleado
    @Query("SELECT SUM(ln.salario) FROM LineaNomina ln " +
            "JOIN ln.nomina n " +
            "WHERE n.empleado.id = :empleadoId AND n.anio = :anio")
    Double findSueldoBrutoAnual(@Param("empleadoId") UUID empleadoId, @Param("anio") int anio);

    Optional<Empleado> findByPersonaNombre(String juan);
}
