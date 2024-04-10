package com.lab4.tpgrupal.controllers;


import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.services.NoticiaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/noticias")
public class NoticiaController extends BaseControllerImpl<Noticia, NoticiaServiceImpl> {
}
