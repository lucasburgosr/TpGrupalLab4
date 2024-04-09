package com.lab4.tpgrupal.servicios;

import com.lab4.tpgrupal.entidades.Noticia;
import com.lab4.tpgrupal.repositorios.BaseRepositorio;
import com.lab4.tpgrupal.repositorios.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicioImpl extends BaseServicioImpl<Noticia, Integer> implements NoticiaServicio {

    @Autowired
    private NoticiaRepositorio autorRepository;

    public NoticiaServicioImpl(BaseRepositorio<Noticia, Integer> baseRepository) {
        super(baseRepository);
    }
}
