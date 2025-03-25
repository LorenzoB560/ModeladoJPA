package com.lbodaszsservidor.modeladojpa.component;

import com.lbodaszsservidor.modeladojpa.entity.Departamento;
import com.lbodaszsservidor.modeladojpa.repository.DepartamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CargaDatos implements CommandLineRunner {

    DepartamentoRepository departamentoRepository;

    public CargaDatos(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        eliminarDepartamentos();
        crearDepartamentos();
        listarDepartamentos();
    }

    // Método para eliminar todos los departamentos
    public void eliminarDepartamentos() {
        departamentoRepository.deleteAll();
        System.err.println("✅ Todos los departamentos han sido eliminados.");
    }

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
