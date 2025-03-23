package com.lbodaszsservidor.modeladojpa.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscripcion",
        uniqueConstraints = { @UniqueConstraint(name = "UK_empleado_edicion", columnNames = { "id_emp", "id_edicion" }) })
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable = false, foreignKey = @ForeignKey(name = "FK_inscripcion_empleado"))
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_edicion", nullable = false, foreignKey = @ForeignKey(name = "FK_inscripcion_edicion"))
    private EdicionCurso edicionCurso;

    private Double notaFinal;
}

