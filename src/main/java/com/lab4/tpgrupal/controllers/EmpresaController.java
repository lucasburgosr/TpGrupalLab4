package com.lab4.tpgrupal.controllers;

import com.lab4.tpgrupal.entities.Empresa;
import com.lab4.tpgrupal.services.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("empresa", new Empresa()); // Agregar un objeto Empresa vacío al modelo
        return "agregarEmpresa"; // Devolver el nombre de la vista para mostrar
    }




    @PostMapping("/agregar")
    public String agregarEmpresa(@ModelAttribute Empresa empresa, Model model) {
        try {
            empresaServicio.crear(empresa); // Guarda la nueva empresa en la base de datos
            List<Empresa> empresas = empresaServicio.buscarTodas(); // Obtén la lista actualizada de empresas
            model.addAttribute("empresas", empresas); // Agrega la lista de empresas al modelo para mostrarlas en la vista
            System.out.println(empresa.getDenominacion());
            return "index"; // Redirige a la página principal
        } catch (Exception e) {
            // Manejo de errores
            return "error";
        }

    }






}
