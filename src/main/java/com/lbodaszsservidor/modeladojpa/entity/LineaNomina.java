package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "linea_nomina")
public class LineaNomina {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_lineaNomina")
    private UUID id;

    private double salario;
    private double conceptos;
    private double liquido;

    @ManyToOne
    @JoinColumn(name = "id_nomina", nullable = false, foreignKey = @ForeignKey(name = "FK_linea_nomina_nomina_id_linea_nomina"))
    private Nomina nomina;

    public LineaNomina(double salario, double conceptos, double liquido, Nomina nomina) {
        this.id = UUID.randomUUID();
        this.salario = salario;
        this.conceptos = conceptos;
        this.liquido = liquido;
        this.nomina = nomina;
    }
}