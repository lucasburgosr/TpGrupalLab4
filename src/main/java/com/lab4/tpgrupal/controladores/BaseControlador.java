package com.lab4.tpgrupal.controladores;

import com.lab4.tpgrupal.entidades.Base;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseControlador<E extends Base, ID extends Serializable> {

    public ResponseEntity<?> obtenerTodas();
    public ResponseEntity<?> obtenerPorId(@PathVariable ID id);
    public ResponseEntity<?> crear(@RequestBody E entity);
    public ResponseEntity<?> actualizar(@PathVariable ID id, @RequestBody E entity);
    public ResponseEntity<?> eliminar(@PathVariable ID id);
}
