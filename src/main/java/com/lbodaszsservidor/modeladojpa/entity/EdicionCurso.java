package com.lbodaszsservidor.modeladojpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "edicion_curso")
public class EdicionCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_edicion")
    private UUID id;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "FK_edicion_curso"))
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_ponente", nullable = false, foreignKey = @ForeignKey(name = "FK_edicion_ponente"))
    private Empleado ponente;

    @OneToMany(mappedBy = "edicionCurso", cascade = CascadeType.ALL)
    private Set<Inscripcion> inscripciones;
}

