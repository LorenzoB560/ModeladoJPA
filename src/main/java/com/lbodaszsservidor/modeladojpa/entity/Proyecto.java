package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proy", uniqueConstraints = {
        @UniqueConstraint(name = "UK_proyecto_nombre", columnNames = "nombre")
})
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JoinColumn(name = "id_proy")
    private UUID id;

    private String nombre;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpleadoProyecto> empleadosProyectos;
}
