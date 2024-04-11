package com.lab4.tpgrupal.controllers;

import com.lab4.tpgrupal.entities.Empresa;
import com.lab4.tpgrupal.services.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

            // Imprimir atributos de cada empresa
            for (Empresa empresa : empresas) {
                System.out.println("Denominación: " + empresa.getDenominacion());
            }
            return "index";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/home/{id}")
    public String mostrarHome(@PathVariable Integer id, Model model) {
        try {
            Empresa empresa = empresaServicio.buscarPorId(id);
            model.addAttribute("empresa", empresa);
            System.out.println(empresa.getHorarioAtencion());
            return "home";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/agregarEmpresa")
    public String vistaAgregarEmpresa() {
        try {
            return "agregarEmpresa";
        } catch (Exception e) {
            return "error";
        }
    }




}
