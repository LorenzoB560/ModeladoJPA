package com.lbodaszsservidor.modeladojpa.component;

import com.lbodaszsservidor.modeladojpa.entity.Departamento;
import com.lbodaszsservidor.modeladojpa.entity.Empleado;
import com.lbodaszsservidor.modeladojpa.entity.LineaNomina;
import com.lbodaszsservidor.modeladojpa.entity.Nomina;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Direccion;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Genero;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import com.lbodaszsservidor.modeladojpa.repository.DepartamentoRepository;
import com.lbodaszsservidor.modeladojpa.repository.EmpleadoRepository;
import com.lbodaszsservidor.modeladojpa.repository.LineaNominaRepository;
import com.lbodaszsservidor.modeladojpa.repository.NominaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class CargaDatos implements CommandLineRunner {

    EmpleadoRepository empleadoRepository;
    DepartamentoRepository departamentoRepository;
    NominaRepository nominaRepository;
    LineaNominaRepository lineaNominaRepository;

    public CargaDatos(EmpleadoRepository empleadoRepository, DepartamentoRepository departamentoRepository, NominaRepository nominaRepository, LineaNominaRepository lineaNominaRepository) {
        this.departamentoRepository = departamentoRepository;
        this.empleadoRepository = empleadoRepository;
        this.nominaRepository = nominaRepository;
        this.lineaNominaRepository = lineaNominaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        insertarDepartamento();
        insertarEmpleado();
        insertarNominaYLineas();

        listarDepartamentos();

    }

    public void insertarEmpleado() {
        String nombreDepartamento = "Tecnología";

        // Buscar el departamento por nombre
        Departamento departamento = departamentoRepository.findByNombre(nombreDepartamento)
                .orElseThrow(() -> new RuntimeException("Departamento '" + nombreDepartamento + "' no encontrado"));

        // Crear un objeto Dirección
        Direccion direccion = new Direccion();
        direccion.setTipoVia("Calle");
        direccion.setVia("Falsa");
        direccion.setNumero(123);
        direccion.setPiso(2);
        direccion.setPuerta("A");
        direccion.setLocalidad("Madrid");
        direccion.setCodigoPostal(28001);
        direccion.setRegion("Madrid");
        direccion.setPais("España");

        // Crear una Persona con la dirección
        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellidos("Pérez");
        persona.setFechaNacimiento(LocalDate.of(1985, 5, 20));
        persona.setGenero(Genero.MASCULINO);
        persona.setDireccionPostal(direccion);

        // Crear un objeto Periodo
        Periodo periodo = new Periodo(LocalDate.of(2020, 1, 1), LocalDate.of(2023, 12, 31));

        // Crear el objeto Empleado con UUID generado automáticamente
        Empleado empleado = new Empleado();
        empleado.setPersona(persona);
        empleado.setPeriodo(periodo);
        empleado.setMotivoCese("Renuncia");
        empleado.setFechaEliminacion(null);
        empleado.setDepartamento(departamento);

        // Guardar el empleado en la base de datos
        empleadoRepository.save(empleado);

        System.out.println("Empleado insertado: " + empleado);

    }


    public void insertarDepartamento() {
        String nombreDepartamento = "Tecnología";

        // Verificar si el departamento ya existe por nombre
        Optional<Departamento> departamentoExistente = departamentoRepository.findByNombre(nombreDepartamento);

        if (departamentoExistente.isPresent()) {
            System.out.println("El departamento '" + nombreDepartamento + "' ya existe. No se insertará.");
            return;
        }

        // Crear el objeto Departamento con UUID generado automáticamente
        Departamento departamento = new Departamento();
        departamento.setNombre(nombreDepartamento);
        departamento.setLocalidad("Madrid");
        departamento.setPresupuesto(500000.00); // Presupuesto del departamento

        departamentoRepository.save(departamento);
        System.out.println("Departamento insertado: " + departamento);



        Departamento d2 = new Departamento();
        nombreDepartamento = "Ventas";
        d2.setNombre(nombreDepartamento);

        // Verificar si el departamento ya existe por nombre
        departamentoExistente = departamentoRepository.findByNombre(nombreDepartamento);

        if (departamentoExistente.isPresent()) {
            System.out.println("El departamento '" + nombreDepartamento + "' ya existe. No se insertará.");
            return;
        }
        d2.setLocalidad("Soria");
        d2.setPresupuesto(87000.00);
        departamentoRepository.save(d2);
    }


    public void insertarNominaYLineas() {
        // Buscar al empleado Juan
        Empleado juan = empleadoRepository.findByPersonaNombre("Juan").orElse(null);

        if (juan == null) {
            System.out.println("No se encontró al empleado Juan.");
            return;
        }

        // Verificar si ya existe una nómina para Juan en el mes y año especificado
        if (nominaRepository.existsByEmpleadoAndMesAndAnio(juan, "Marzo", 2024)) {
            System.out.println("La nómina de Juan para Marzo 2024 ya existe. No se insertará.");
            return;
        }

        // Crear nómina
        Nomina nomina = new Nomina();
        nomina.setMes("Marzo");
        nomina.setAnio(2024);
        nomina.setEmpleado(juan);

        // Guardar nómina
        nominaRepository.save(nomina);

        // Crear y guardar líneas de nómina
        LineaNomina lineasNomina1 = new LineaNomina();
        lineasNomina1.setSalario(2500.00);
        lineasNomina1.setConceptos(500);
        lineasNomina1.setLiquido(2000.00);
        lineasNomina1.setNomina(nomina);

        LineaNomina lineasNomina2 = new LineaNomina();
        lineasNomina2.setSalario(2700.00);
        lineasNomina2.setConceptos(600);
        lineasNomina2.setLiquido(2100.00);
        lineasNomina2.setNomina(nomina);

        lineaNominaRepository.save(lineasNomina1);
        lineaNominaRepository.save(lineasNomina2);
    }

    public void listarDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        if (departamentos.isEmpty()) {
            System.err.println("⚠ No hay departamentos registrados.");
        } else {
            System.err.println("📋 Listado de departamentos:");
            departamentos.forEach(System.err::println);
        }
    }
}
