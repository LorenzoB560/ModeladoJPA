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

    @OneToOne
    @JoinColumn(name = "direccion_postal_id", foreignKey = @ForeignKey(name = "FK_persona_direccion"))
    private Direccion direccionPostal;

}
