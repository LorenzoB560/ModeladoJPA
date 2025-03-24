package com.lbodaszsservidor.modeladojpa.entity.auxiliar;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Persona {

    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "tipoVia", column = @Column(name = "tipoVia_personal")),
            @AttributeOverride(name = "via", column = @Column(name = "via_personal")),
            @AttributeOverride(name = "numero", column = @Column(name = "numero_personal")),
            @AttributeOverride(name = "piso", column = @Column(name = "piso_personal")),
            @AttributeOverride(name = "puerta", column = @Column(name = "puerta_personal")),
            @AttributeOverride(name = "localidad", column = @Column(name = "localidad_personal")),
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "cp_personal")),
            @AttributeOverride(name = "region", column = @Column(name = "region_personal")),
            @AttributeOverride(name = "pais", column = @Column(name = "pais_personal"))
    })
    private Direccion direccionPostal;

}
