package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "empleado_proyecto")
public class EmpleadoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp_proy")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_proyecto_empleado"))
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_proy", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_proyecto_proyecto"))
    private Proyecto proyecto;

    private String rol;
    private Periodo periodo;
}

