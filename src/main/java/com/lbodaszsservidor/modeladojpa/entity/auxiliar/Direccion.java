package com.lbodaszsservidor.modeladojpa.entity.auxiliar;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long id;

    private String tipoVia;
    private String via;
    private int numero;
    private int piso;
    private String puerta;
    private String localidad;

    @Column(name = "cp")
    private int codigoPostal;
    private String region;
    private String pais;

}
