package com.lbodaszsservidor.modeladojpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    private String contrasenia;

    @Transient
    private String confirmacionContrasenia;

    private LocalDate fechaUltimoAcceso;

    @OneToOne
    @JoinColumn(name = "id_emp", nullable = false, unique = true)
    private Empleado empleado;
}
