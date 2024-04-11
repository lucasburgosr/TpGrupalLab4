package com.lab4.tpgrupal.controllers;


import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.services.NoticiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/noticias")
public class NoticiaController extends BaseControllerImpl<Noticia, NoticiaServiceImpl> {

    @Autowired
    NoticiaServiceImpl noticiaService;
    
    @GetMapping("/buscador")
    public String mostrarNoticias(Model model) {
        try {
            List<Noticia> noticias = noticiaService.buscarTodas();
            model.addAttribute("noticias", noticias);

            // Imprimir atributos de cada noticia
            for (Noticia noticia : noticias) {
                System.out.println("Título noticia: " + noticia.getTituloNoticia());
            }
            
            return "buscador";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleNoticia(@PathVariable Integer id, Model model) {
        try {
            Noticia noticia = noticiaService.buscarPorId(id);
            model.addAttribute("noticia", noticia);
            System.out.println(noticia.getTituloNoticia());
            return "detalle";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/")
    public String agregarnoticia(@ModelAttribute Noticia noticia) {
        try {
            noticiaService.crear(noticia);
            return "redirect:/noticias/buscador";
        } catch (Exception e) {

            return "error";
        }
    }
}
