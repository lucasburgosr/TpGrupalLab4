package com.lab4.tpgrupal.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "horarioAtencion")
    private String horarioAtencion;
    @Column(name = "quienesSomos")
    private String quienesSomos;
    @Column(name = "latitud")
    private Float latitud;
    @Column(name = "longitud")
    private Float longitud;
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "email")
    private String email;
}
