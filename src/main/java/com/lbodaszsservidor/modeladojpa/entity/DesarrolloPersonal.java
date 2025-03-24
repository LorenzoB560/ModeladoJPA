package com.lbodaszsservidor.modeladojpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "desarrollo_personal")
public class DesarrolloPersonal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_des")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "desarrolloPersonal")
    private Set<Empleado> empleado;
}

