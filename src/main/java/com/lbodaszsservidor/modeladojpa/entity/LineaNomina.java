package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class LineaNomina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lineaNomina")
    private long id;

    private double salario;
    private double conceptos;
    private double liquido;

    @ManyToOne
    @JoinColumn(name = "id_nomina", nullable = false, foreignKey = @ForeignKey(name = "FK_linea_nomina_nomina_id_linea_nomina"))
    private Nomina nomina;
}