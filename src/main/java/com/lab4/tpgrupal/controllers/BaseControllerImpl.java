package com.lab4.tpgrupal.controllers;

import com.lab4.tpgrupal.entities.Base;
import com.lab4.tpgrupal.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Integer>> implements BaseController<E, Integer> {

    @Autowired
    protected S servicio;

    @GetMapping("")
    public ResponseEntity<?> obtenerTodas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarTodas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.crear(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizar(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.eliminar(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

}
