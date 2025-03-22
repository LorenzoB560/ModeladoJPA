package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.TarjetaCredito;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "info_eco")
public class InformacionEconomica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info_eco")
    private int id;

    private double salario;
    private double comision;
    private String banco;

    //validar cuenta
    private long cuentaBancaria;

    private TarjetaCredito tarjetaCredito;

    @OneToMany (mappedBy = "informacionEconomica", cascade = CascadeType.ALL)
    private Set<Nomina> nominas;

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable=false, foreignKey = @ForeignKey(name = "FK_info_eco_empleado_id_emp"))
    private Empleado empleado;
}
