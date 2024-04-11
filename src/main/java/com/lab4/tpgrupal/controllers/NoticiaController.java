package com.lab4.tpgrupal.controllers;


import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.services.NoticiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String agregarNoticia(@RequestParam String tituloNoticia, @RequestParam String resumenNoticia, @RequestParam String contenido, @RequestParam MultipartFile imagen) {
        try {
            Noticia noticia = new Noticia();
            noticia.setTituloNoticia(tituloNoticia);
            noticia.setResumenNoticia(resumenNoticia);
            noticia.setContenidoHtml(contenido);
            noticia.setFechaPublicacion(LocalDateTime.now());

            byte[] bytesImagen = imagen.getBytes();
            String imagenBase64 = Base64.getEncoder().encodeToString(bytesImagen);

            // Guardar la imagen como una cadena en la base de datos
            noticia.setImagenNoticia(imagenBase64);

            noticiaService.crear(noticia);
            return "redirect:/noticias/buscador";
        } catch (Exception e) {
            return "error";
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarNoticia(@PathVariable Integer id) {
        try {
            noticiaService.eliminar(id);
            return "redirect:/noticias/buscador";
        } catch (Exception e) {
            return "error";
        }
    }
}
