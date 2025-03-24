package com.lbodaszsservidor.modeladojpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_curso")
    private UUID id;

    private String nombre;
    private int duracion;

    // Lista de empleados cualificados para impartirlo
    @ManyToMany(mappedBy = "cursosCualificados")
    private Set<Empleado> empleadosCualificados;

    // Lista de ediciones del curso
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private Set<EdicionCurso> ediciones;
}
