package com.lbodaszsservidor.modeladojpa.entity.auxiliar;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class TarjetaCredito {

    private String numero;
    private int CVV;
    @Past(message = "La fecha debe de estar en el pasado")
    private String fechaCaducidad;
}
