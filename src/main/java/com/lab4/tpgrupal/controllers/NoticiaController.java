package com.lab4.tpgrupal.controllers;


import com.lab4.tpgrupal.entities.Empresa;
import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.services.EmpresaServiceImpl;
import com.lab4.tpgrupal.services.NoticiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/noticias")
public class NoticiaController extends BaseControllerImpl<Noticia, NoticiaServiceImpl> {

    @Autowired
    NoticiaServiceImpl noticiaService;

    @Autowired
    EmpresaServiceImpl empresaService;

    @GetMapping("/detalle/{id}")
    public ModelAndView mostrarDetalleNoticia(@PathVariable Integer id) {
        try {
            Noticia noticia = noticiaService.buscarPorId(id);
            ModelAndView modelAndView = new ModelAndView("detalle"); // Nombre de la vista
            modelAndView.addObject("noticia", noticia); // Agregar la noticia al modelo
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }


    @GetMapping("/agregar")
    public ModelAndView mostrarFormularioAgregar(@RequestParam("id") Integer empresaId) {
        try {
            System.out.println(empresaId);
            ModelAndView modelAndView = new ModelAndView("tiny"); // Nombre de la vista
            modelAndView.addObject("empresaId", empresaId); // Agregar el ID de la empresa al modelo
            modelAndView.addObject("noticia", new Noticia()); // Agregar un objeto Noticia vacío al modelo
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("404");
        }
    }



    @PostMapping("/detalle")
    public ModelAndView agregarNoticia(@ModelAttribute Noticia noticia, @RequestParam("imagen") MultipartFile imagen) {
        try {

            // Establecer la fecha de publicación truncada a solo la fecha
            noticia.setFechaPublicacion(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));

            // Procesar la imagen
            noticia.setImagenNoticia(imagen.getBytes());

            // Crear la noticia
            noticiaService.crear(noticia);

            // Obtener la empresa de la noticia
            Empresa empresa = noticia.getEmpresa();

            ModelAndView modelAndView = new ModelAndView("detalle");
            modelAndView.addObject("noticia", noticia);
            modelAndView.addObject("empresa", empresa);
            return modelAndView;
        } catch (Exception e) {
            // Manejar la excepción en caso de error de entrada/salida al procesar la imagen
            return new ModelAndView("error");
        }
    }

    @GetMapping("/buscador")
    public ModelAndView buscarNoticias(@RequestParam("palabraClave") String palabraClave) {
        try{
            List<Noticia> noticias = noticiaService.buscarNoticiasPorPalabraClave(palabraClave);
            ModelAndView modelAndView = new ModelAndView("buscador"); // Nombre de la vista para mostrar los resultados de la búsqueda
            modelAndView.addObject("noticias", noticias);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable Integer id) {
        try {
            noticiaService.eliminar(id);
            return "redirect:/noticias/buscador";
        } catch (Exception e) {
            return "error";
        }
    }
}
