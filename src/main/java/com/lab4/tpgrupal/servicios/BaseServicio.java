package com.lab4.tpgrupal.servicios;

import com.lab4.tpgrupal.entidades.Base;

import java.io.Serializable;
import java.util.List;

public interface BaseServicio<E extends Base, ID extends Serializable> {
    public List<E> buscarTodas() throws Exception;
    public E buscarPorId(ID id) throws Exception;
    public E crear(E entity) throws Exception;
    public E actualizar(ID id, E entity) throws Exception;
    public boolean eliminar(ID id) throws Exception;
}
