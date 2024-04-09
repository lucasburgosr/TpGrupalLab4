package com.lab4.tpgrupal.controladores;

import com.lab4.tpgrupal.entidades.Empresa;
import com.lab4.tpgrupal.servicios.EmpresaServicioImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/empresas")
public class EmpresaControlador extends BaseControladorImpl<Empresa, EmpresaServicioImpl> {
}
