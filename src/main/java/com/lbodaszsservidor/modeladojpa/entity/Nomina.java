package com.lbodaszsservidor.modeladojpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "nomina")
public class Nomina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nomina")
    private long id;

    private String mes;
    private int anio;

    @OneToMany(mappedBy = "nomina", cascade = CascadeType.ALL)
    private Set<LineaNomina> lineaNominas;

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable = false, foreignKey = @ForeignKey(name = "FK_nomina_empleado_id_nomina"))
    private Empleado empleado;

}


// Todos los datos económicos del empleado
//@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
//private Set<InformacionEconomica> informacionEconomica;