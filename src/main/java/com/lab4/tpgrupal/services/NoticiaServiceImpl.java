package com.lab4.tpgrupal.services;

import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.repositories.BaseRepository;
import com.lab4.tpgrupal.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaServiceImpl extends BaseServiceImpl<Noticia, Integer> implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public NoticiaServiceImpl(BaseRepository<Noticia, Integer> baseRepository) {
        super(baseRepository);
        this.noticiaRepository = noticiaRepository;
    }
    public List<Noticia> obtenerUltimas5Noticias() {
        return noticiaRepository.findTop5ByOrderByFechaPublicacionDesc();
    }
    public List<Noticia> buscarNoticiasPorPalabraClave(String palabraClave) {
        return noticiaRepository.findByTituloNoticiaContainingIgnoreCaseOrResumenNoticiaContainingIgnoreCaseOrContenidoHtmlContainingIgnoreCase(palabraClave, palabraClave, palabraClave);
    }

    public List<Noticia> buscarPorEmpresa(Integer idEmpresa) {
        return noticiaRepository.findByEmpresaIdOrderByFechaPublicacionDesc(idEmpresa);
    }
}
