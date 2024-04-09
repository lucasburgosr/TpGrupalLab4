package com.lab4.tpgrupal.repositorios;

import com.lab4.tpgrupal.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends BaseRepositorio<Noticia, Integer> {
}
