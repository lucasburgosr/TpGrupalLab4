package com.lab4.tpgrupal.controladores;


import com.lab4.tpgrupal.entidades.Empresa;
import com.lab4.tpgrupal.entidades.Noticia;
import com.lab4.tpgrupal.servicios.EmpresaServicioImpl;
import com.lab4.tpgrupal.servicios.NoticiaServicioImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/noticias")
public class NoticiaControlador extends BaseControladorImpl<Noticia, NoticiaServicioImpl> {
}
