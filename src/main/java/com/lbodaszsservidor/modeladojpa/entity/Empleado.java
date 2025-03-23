package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "emp")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private int id;

    private Persona persona;
    private Periodo periodo;
    private String motivoCese;

    private Usuario usuario;


    // Todos los puestos que ha tenido un empleado
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistorialPuesto> historialPuestos;

    // Todos los datos económicos del empleado
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InformacionEconomica> informacionEconomica;

    // A qué becario le imparte clase
    @OneToOne(mappedBy = "mentor")
    private Becario becario;


    // Cursos para los que está cualificado como ponente
    @ManyToMany
    @JoinTable(name = "empleado_cualificado",
            joinColumns = @JoinColumn(name = "id_emp"),
            inverseJoinColumns = @JoinColumn(name = "id_curso"))
    private Set<Curso> cursosCualificados;

    // Ediciones que ha impartido como ponente
    @OneToMany(mappedBy = "ponente", cascade = CascadeType.ALL)
    private Set<EdicionCurso> edicionesImpartidas;

    // Ediciones en las que está inscrito como alumno
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Set<Inscripcion> inscripciones;

    @ManyToOne
    @JoinColumn(name = "id_dept", foreignKey = @ForeignKey(name = "FK_empleado_departamento"))
    private Departamento departamento;


    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpleadoProyecto> empleadosProyectos;

    @ManyToMany
    @JoinTable(name = "desarrollo_empleado",
            joinColumns = @JoinColumn(name = "id_emp"), foreignKey = @ForeignKey(name = "FK_desarrollo_empleado_empleado"),
            inverseJoinColumns = @JoinColumn(name = "id_des"), inverseForeignKey = @ForeignKey(name = "FK_desarrollo_empleado_desarrollo"))
    private Set<DesarrolloPersonal>  desarrolloPersonal;
}

