package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "emp")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Persona persona;
    private Periodo periodo;
    private String motivoCese;

    @OneToMany
    private Set<HistorialPuesto> historialPuestos;
    @OneToMany
    private Set<InformacionEconomica> informacionEconomica;

    //private HistorialPuesto historialPuesto;

}
