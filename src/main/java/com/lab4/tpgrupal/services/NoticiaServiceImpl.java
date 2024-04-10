package com.lab4.tpgrupal.services;

import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.repositories.BaseRepository;
import com.lab4.tpgrupal.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServiceImpl extends BaseServiceImpl<Noticia, Integer> implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public NoticiaServiceImpl(BaseRepository<Noticia, Integer> baseRepository) {
        super(baseRepository);
        this.noticiaRepository = noticiaRepository;
    }
}
