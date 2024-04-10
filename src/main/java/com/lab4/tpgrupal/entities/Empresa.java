package com.lab4.tpgrupal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa extends Base {

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
