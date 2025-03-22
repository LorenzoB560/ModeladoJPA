package com.lbodaszsservidor.modeladojpa.entity.auxiliar;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private LocalDate fechaUltimoAcceso;
}
