package com.lab4.tpgrupal.repositories;

import com.lab4.tpgrupal.entities.Noticia;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends BaseRepository<Noticia, Integer> {
    List<Noticia> findByTituloNoticiaContainingIgnoreCaseOrResumenNoticiaContainingIgnoreCaseOrContenidoHtmlContainingIgnoreCase(String palabraClave, String palabraClave2, String palabraClave3);
    List<Noticia> findTop5ByOrderByFechaPublicacionDesc();
    List<Noticia> findByEmpresaIdOrderByFechaPublicacionDesc(Integer idEmpresa);

}

