package com.lbodaszsservidor.modeladojpa.component;

import com.lbodaszsservidor.modeladojpa.entity.Departamento;
import com.lbodaszsservidor.modeladojpa.entity.Empleado;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Direccion;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Genero;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import com.lbodaszsservidor.modeladojpa.repository.DepartamentoRepository;
import com.lbodaszsservidor.modeladojpa.repository.EmpleadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CargaDatos implements CommandLineRunner {

    EmpleadoRepository empleadoRepository;
    DepartamentoRepository departamentoRepository;

    public CargaDatos(EmpleadoRepository empleadoRepository, DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
        this.empleadoRepository = empleadoRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        insertarEmpleado();
        insertarDepartamento();


        //empleadoRepository.findAll().forEach(System.out::println);
    }

    public void insertarEmpleado() {
        // Crear un objeto Direccion
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

        // Crear un objeto Persona e incluir la dirección
        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellidos("Pérez");
        persona.setFechaNacimiento(LocalDate.of(1985, 5, 20));
        persona.setGenero(Genero.MASCULINO); // Asumimos que es masculino (puedes cambiarlo según sea necesario)
        persona.setDireccionPostal(direccion); // Asignar la dirección a la persona

        // Crear un objeto Periodo
        Periodo periodo = new Periodo(LocalDate.of(2020, 1, 1), LocalDate.of(2023, 12, 31));

        // Crear el objeto Empleado
        Empleado empleado = new Empleado();
        empleado.setPersona(persona);
        empleado.setPeriodo(periodo);
        empleado.setMotivoCese("Renuncia");
        empleado.setFechaEliminacion(null); // No tiene fecha de eliminación

        // Guardar el empleado en la base de datos
        empleadoRepository.save(empleado);

        System.out.println("Empleado insertado: " + empleado);
    }

    public void insertarDepartamento() {
        // Crear el objeto Departamento
        Departamento departamento = new Departamento();
        departamento.setNombre("Tecnología");
        departamento.setLocalidad("Madrid");
        departamento.setPresupuesto(500000.00); // Presupuesto del departamento

        // Buscar un jefe existente para asignar al departamento
        /*Optional<Empleado> jefeOpt = empleadoRepository.findById(1L); // ID del jefe (puedes cambiar el ID según sea necesario)

        if (!jefeOpt.isPresent()) {
            System.out.println("Jefe no encontrado");
            return; // Salir si el jefe no existe
        }

        Empleado jefe = jefeOpt.get(); // Obtener al jefe desde el repositorio

        // Asignar al jefe al departamento
        departamento.setJefe(jefe);
        */
        // Guardar el departamento en la base de datos
        departamentoRepository.save(departamento);

        System.out.println("Departamento insertado: " + departamento);
    }







    /*public void listarEmpleadosDeDepartamento(UUID departamentoId) {
        // Buscar el departamento
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(departamentoId);

        if (!departamentoOpt.isPresent()) {
            System.out.println("Departamento no encontrado.");
            return; // Si no se encuentra el departamento, salimos
        }

        Departamento departamento = departamentoOpt.get();

        // Obtener los empleados de ese departamento
        //List<Empleado> empleados = empleadoRepository.findByDepartamento(departamento);

        // Mostrar los empleados
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en este departamento.");
        } else {
            System.out.println("Empleados del departamento " + departamento.getNombre() + ":");
            for (Empleado empleado : empleados) {
                System.out.println("- " + empleado.getPersona().getNombre() + " " + empleado.getPersona().getApellidos());
            }
        }
    }*/




    public void crearDepartamentos() {
        Departamento d1 = new Departamento();
        d1.setNombre("Ventas");
        d1.setLocalidad("Huelva");
        d1.setPresupuesto(162000.00);

        Departamento d2 = new Departamento();
        d2.setNombre("Almacén");
        d2.setLocalidad("Soria");
        d2.setPresupuesto(87000.00);

        departamentoRepository.saveAll(List.of(d1, d2));

        System.err.println("✅ Departamentos insertados correctamente.");
    }




    // Método para listar todos los departamentos
    public void listarDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        if (departamentos.isEmpty()) {
            System.err.println("⚠ No hay departamentos registrados.");
        } else {
            System.err.println("📋 Listado de departamentos:");
            departamentos.forEach(System.err::println);
        }
    }

    public void eliminarEmpleado(UUID id) {
        empleadoRepository.findById(id).ifPresent(emp -> {
            emp.setFechaEliminacion(LocalDate.now());
            empleadoRepository.save(emp);
        });
    }

    public void eliminarDepartamentos() {
        departamentoRepository.deleteAll();
        System.err.println("✅ Todos los departamentos han sido eliminados.");
    }

    private boolean isValidCreditCard(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        // Iterate from right to left
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));

            // Double every second digit
            if (alternate) {
                n *= 2;
                // If the result is greater than 9, subtract 9
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate; // Flip the alternate flag
        }

        // The number is valid if the total sum is a multiple of 10
        return (sum % 10 == 0);
    }
}
