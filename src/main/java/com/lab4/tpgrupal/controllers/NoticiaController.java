package com.lab4.tpgrupal.controllers;


import com.lab4.tpgrupal.entities.Empresa;
import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.services.EmpresaServiceImpl;
import com.lab4.tpgrupal.services.NoticiaService;
import com.lab4.tpgrupal.services.NoticiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            Empresa empresa = noticia.getEmpresa();
            System.out.print(empresa.getDenominacion());
            ModelAndView modelAndView = new ModelAndView("detalle"); // Nombre de la vista
            modelAndView.addObject("noticia", noticia); // Agregar la noticia al modelo
            modelAndView.addObject("empresa", empresa);
            modelAndView.addObject("rutaImagen", "/images/imagenesnoticias/" + noticia.getImagenNoticia());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("404");
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


    @PostMapping("/agregar")
    public ModelAndView agregarNoticia(@ModelAttribute Noticia noticia, @RequestParam("archivoImagen") MultipartFile archivoImagen) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            noticia.setFechaPublicacion(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));

            String nombreImagen = noticiaService.guardarImagen(archivoImagen);


            noticia.setImagenNoticia(nombreImagen);

            noticiaService.crear(noticia);

            // Obtener el ID de la noticia recién creada
            Integer idNuevaNoticia = noticia.getId();

            modelAndView.setViewName("redirect:/noticias/detalle/" + idNuevaNoticia); // Redirigir a la página detalle de la nueva noticia
        } catch (Exception e) {
            // Manejar la excepción en caso de error de entrada/salida al procesar la imagen
            modelAndView.setViewName("404");
        }
        return modelAndView;
    }


    @GetMapping("/buscador")
    public ModelAndView buscarNoticias(@RequestParam("palabraClave") String palabraClave) {
        try {
            List<Noticia> noticias = noticiaService.buscarNoticiasPorPalabraClave(palabraClave);
            Empresa empresa = noticias.get(0).getEmpresa();
            ModelAndView modelAndView = new ModelAndView("buscador"); // Nombre de la vista para mostrar los resultados de la búsqueda
            modelAndView.addObject("noticias", noticias);
            modelAndView.addObject("empresa", empresa);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @GetMapping("/noticias/actualizar/{id}")
    public ModelAndView mostrarFormularioActualizacion(@PathVariable("id") Integer id) {
        try {
            // Obtener la noticia por su ID
            Noticia noticia = noticiaService.buscarPorId(id);
            if (noticia == null) {
                // Manejar el caso en que la noticia no existe
                return new ModelAndView("error");
            }
            // Cargar el formulario de edición con la noticia
            ModelAndView modelAndView = new ModelAndView("tiny");
            modelAndView.addObject("noticia", noticia);
            return modelAndView;
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            return new ModelAndView("error");
        }
    }

    @PostMapping("/noticias/actualizar")
    public ModelAndView procesarActualizacion(@ModelAttribute Noticia noticia) {
        try {
            // Actualizar la noticia en la base de datos
            noticiaService.actualizar(noticia.getId(), noticia);
            // Redirigir a la vista de detalle de la noticia actualizada
            return new ModelAndView("redirect:/noticias/detalle/" + noticia.getId());
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
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
