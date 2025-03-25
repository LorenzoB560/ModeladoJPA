package com.lbodaszsservidor.modeladojpa.entity;

import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Periodo;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.Persona;
import com.lbodaszsservidor.modeladojpa.entity.auxiliar.TarjetaCredito;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "emp")
@SecondaryTable(name = "info_eco", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_emp"))
public class Empleado{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_emp")
    private UUID id;

    private Persona persona;
    private Periodo periodo;
    private String motivoCese;


    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_empleado_usuario_id_usuario"))
    private Usuario usuario;

    //INFORMACIÓN ECONÓMICA

    @Column(name = "comision", table = "info_eco")
    private double comision;
    @Column(name = "banco", table = "info_eco")
    private String banco;

    //validar cuenta
    @Column(name = "cuenta_bancaria", table = "info_eco")
    @Size(min = 20, max = 20, message = "La cuenta bancaria debe tener 20 dígitos")
    private String cuentaBancaria;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "numero", column = @Column(name = "numero_tarjeta", table = "info_eco")),
            @AttributeOverride(name = "CVV", column = @Column(name = "cvv", table = "info_eco")),
            @AttributeOverride(name = "fechaCaducidad", column = @Column(name = "fecha_caducidad", table = "info_eco"))
    })
    private TarjetaCredito tarjetaCredito;

    //NOMINAS
    @Column(name = "mes_nomina", table = "info_eco")
    private String mes;
    @Column(name = "anio_nomina", table = "info_eco")
    private int anio;

    //LINEA NOMINA

    @Column(name = "salario_nomina", table = "info_eco")
    private double salario;
    @Column(name = "concepto_nomina", table = "info_eco")
    private double conceptos;
    @Column(name = "liquido_nomina", table = "info_eco")
    private double liquido;

    // Todos los puestos que ha tenido un empleado
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistorialPuesto> historialPuestos;



    // A qué becario le imparte clase
    @OneToOne(mappedBy = "mentor")
    @JoinColumn(name = "id_becario", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_usuario_becario_id_becario"))
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

    // JERARQUÍA EMPRESARIAL
    @ManyToOne
    @JoinColumn(name = "id_jefe", foreignKey = @ForeignKey(name = "FK_empleado_jefe"))
    private Empleado jefe;

    @OneToMany(mappedBy = "jefe", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Empleado> subordinados;
}

