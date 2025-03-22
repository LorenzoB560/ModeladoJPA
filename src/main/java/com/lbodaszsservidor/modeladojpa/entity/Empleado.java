package com.lbodaszsservidor.modeladojpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "emp")
public class Empleado {

    @Id
    private UUID id;

    private String nombre;
}
