package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Direccion;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "becario")
public class Becario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_becario")
    private UUID id;

    private Persona persona;
    private String nombreCentro;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "tipoVia", column = @Column(name = "tipoVia_estudios")),
            @AttributeOverride(name = "via", column = @Column(name = "via_estudios")),
            @AttributeOverride(name = "numero", column = @Column(name = "numero_estudios")),
            @AttributeOverride(name = "piso", column = @Column(name = "piso_estudios")),
            @AttributeOverride(name = "puerta", column = @Column(name = "puerta_estudios")),
            @AttributeOverride(name = "localidad", column = @Column(name = "localidad_estudios")),
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "cp_estudios")),
            @AttributeOverride(name = "region", column = @Column(name = "region_estudios")),
            @AttributeOverride(name = "pais", column = @Column(name = "pais_estudios"))
    })
    private Direccion direccionEstudios;

    @OneToOne
    @JoinColumn(name = "id_mentor", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_becario_empleado_id_empleado"))
    private Empleado mentor;
}
