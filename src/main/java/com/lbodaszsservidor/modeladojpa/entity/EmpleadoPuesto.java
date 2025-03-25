package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "empleado_puesto")
public class EmpleadoPuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_empleado_puesto")
    private UUID id;

    private Periodo periodo;
    private double salario;

    @ManyToOne
    @JoinColumn(name = "id_emp")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_puesto")
    private Puesto puesto;

}
