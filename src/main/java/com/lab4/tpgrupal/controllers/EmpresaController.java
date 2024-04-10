package com.lab4.tpgrupal.controllers;

import com.lab4.tpgrupal.entities.Empresa;
import com.lab4.tpgrupal.services.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/empresas")
public class EmpresaController extends BaseControllerImpl<Empresa, EmpresaServiceImpl> {

    @Autowired
    private EmpresaServiceImpl empresaServicio;
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
