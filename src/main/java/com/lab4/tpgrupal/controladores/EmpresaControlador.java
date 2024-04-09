package com.lab4.tpgrupal.controladores;

import com.lab4.tpgrupal.entidades.Empresa;
import com.lab4.tpgrupal.servicios.EmpresaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/empresas")
public class EmpresaControlador extends BaseControladorImpl<Empresa, EmpresaServicioImpl> {

    @Autowired
    private EmpresaServicioImpl empresaServicio;
    @GetMapping("/listaEmpresas")
    public String mostrarTodasLasEmpresas(Model model) {
        try {
            List<Empresa> empresas = empresaServicio.buscarTodas();
            model.addAttribute("empresas", empresas);
            return "index";
        } catch (Exception e) {
            return "error";
        }
    }
}
