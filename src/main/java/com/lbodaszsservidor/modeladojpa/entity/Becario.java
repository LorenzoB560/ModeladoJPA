package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Direccion;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Becario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Persona persona;
    private String nombreCentro;
    @OneToOne
    @JoinColumn(name = "direccion_estudios_id", foreignKey = @ForeignKey(name = "FK_becario_direccion_estudios"))
    private Direccion direccionEstudios;

    @OneToOne
    @JoinColumn(name = "id_mentor", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_becario_empleado_id_empleado"))
    private Empleado mentor;
}
