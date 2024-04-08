package com.lab4.tpgrupal.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "titulo")
    private String tituloNoticia;
    @Column(name = "resumen")
    private String resumenNoticia;
    @Column(name = "imagen")
    private String imagenNoticia;
    @Column(name = "contenidoHTML")
    private String contenidoHtml;
    @Column(name = "publicada")
    private char publicada;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_empresa")
    private Empresa empresa;

}
