package com.lbodaszsservidor.modeladojpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "info_eco")
public class InformacionEconomica {

    @Id
    private int id;

    private double salario;
    private double comision;
    private String banco;

    //validar cuenta
    private long cuentaBancaria;

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable=false)
    private Empleado empleado;
}
