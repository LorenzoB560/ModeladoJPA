package com.lbodaszsservidor.modeladojpa;

import com.lbodaszsservidor.modeladojpa.entity.Empleado;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import com.lbodaszsservidor.modeladojpa.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ModeladoJpaApplicationTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        // Insertar datos iniciales en la base de datos
        Persona persona = new Persona();
        persona.setNombre("Juan");

        empleado = new Empleado();
        empleado.setId(UUID.randomUUID());
        empleado.setPersona(persona);
        empleado.setPeriodo(new Periodo(LocalDate.of(2020, 1, 1), LocalDate.of(2025, 12, 31)));
        empleadoRepository.save(empleado);
    }

    // 1️⃣ Listado de empleados de un departamento
    @Test
    void testFindByDepartamentoNombre() {
        List<Empleado> empleados = empleadoRepository.findByDepartamentoNombre("Tecnología");
        assertThat(empleados).isNotEmpty();
    }

    // 2️⃣ Lista de empleados por periodo
    @Test
    void testFindEmpleadosByPeriodo() {
        LocalDate inicio = LocalDate.of(2022, 1, 1);
        LocalDate fin = LocalDate.of(2023, 12, 31);
        List<Empleado> empleados = empleadoRepository.findEmpleadosByPeriodo(inicio, fin);
        assertThat(empleados).contains(empleado);
    }

    // 3️⃣ Empleados en un rango salarial
    @Test
    void testFindEmpleadosBySalarioRango() {
        List<Empleado> empleados = empleadoRepository.findEmpleadosBySalarioRango(2000.0, 3000.0);
        assertThat(empleados).isNotEmpty();
    }

    // 4️⃣ Empleados subordinados de un jefe
    @Test
    void testFindByJefeId() {
        List<Empleado> empleados = empleadoRepository.findByJefeId(empleado.getId());
        assertThat(empleados).isEmpty(); // En este caso, aún no hay subordinados
    }

    // 5️⃣ Sueldo líquido en un mes
    @Test
    void testFindSueldoLiquido() {
        Double sueldo = empleadoRepository.findSueldoLiquido(empleado.getId(), "Marzo", 2024);
        assertThat(sueldo).isNotNull();
    }

    // 6️⃣ Sueldo bruto anual
    @Test
    void testFindSueldoBrutoAnual() {
        Double sueldoBruto = empleadoRepository.findSueldoBrutoAnual(empleado.getId(), 2024);
        assertThat(sueldoBruto).isNotNull();
    }
}
