package com.lab4.tpgrupal.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "noticia")
public class Noticia extends Base{

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
