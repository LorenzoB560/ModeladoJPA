package com.lbodaszsservidor.modeladojpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data @AllArgsConstructor
@Entity
@Table(name = "dept", uniqueConstraints = {
        @UniqueConstraint(name = "UK_departamento_nombre", columnNames = "nombre"),
        @UniqueConstraint(name = "UK_departamento_codigo", columnNames = "codigo")
})
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dept")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false, unique = true)
    private String localidad;
    private double presupuesto;

    @OneToOne
    @JoinColumn(name = "id_jefe", unique = true, foreignKey = @ForeignKey(name = "FK_departamento_jefe"))
    private Empleado jefe;
}
