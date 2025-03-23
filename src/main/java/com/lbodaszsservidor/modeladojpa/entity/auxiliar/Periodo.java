package com.lbodaszsservidor.modeladojpa.entity.auxiliar;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Periodo {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
