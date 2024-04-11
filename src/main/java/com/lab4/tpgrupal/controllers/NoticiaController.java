package com.lab4.tpgrupal.controllers;


import com.lab4.tpgrupal.entities.Empresa;
import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.services.NoticiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/noticias")
public class NoticiaController extends BaseControllerImpl<Noticia, NoticiaServiceImpl> {

    @Autowired
    NoticiaServiceImpl noticiaService;

    @GetMapping("/buscador")
    public ModelAndView mostrarNoticias() {
        try {
            List<Noticia> noticias = noticiaService.buscarTodas();
            ModelAndView modelAndView = new ModelAndView("buscador"); // Nombre de la vista
            modelAndView.addObject("noticias", noticias); // Agregar la lista de noticias al modelo

            // Imprimir atributos de cada noticia
            for (Noticia noticia : noticias) {
                System.out.println("Título noticia: " + noticia.getTituloNoticia());
            }

            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }


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
            return new ModelAndView("error");
        }
    }



    @PostMapping("/agregar")
    public String agregarNoticia(@ModelAttribute Noticia noticia, Model model) {
        try {
            noticiaService.crear(noticia);
            List<Noticia> noticias = noticiaService.buscarTodas();
            model.addAttribute("noticias", noticias);
            System.out.println(noticia.getTituloNoticia());
            return "buscador";
        } catch (Exception e) {
            return "error";
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
