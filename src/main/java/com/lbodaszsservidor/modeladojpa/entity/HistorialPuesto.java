package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "historial")
public class HistorialPuesto {

    @Id
    private int id;

    private String puesto;
    private Periodo periodo;
    private double salario;

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable=false)
    private Empleado empleado;
}
