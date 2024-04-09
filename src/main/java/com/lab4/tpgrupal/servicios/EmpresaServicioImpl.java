package com.lab4.tpgrupal.servicios;

import com.lab4.tpgrupal.entidades.Empresa;
import com.lab4.tpgrupal.entidades.Noticia;
import com.lab4.tpgrupal.repositorios.BaseRepositorio;
import com.lab4.tpgrupal.repositorios.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServicioImpl extends BaseServicioImpl<Empresa, Integer> implements EmpresaServicio{

    @Autowired
    private EmpresaRepositorio autorRepository;

    public EmpresaServicioImpl(BaseRepositorio<Empresa, Integer> baseRepository) {
        super(baseRepository);
    }
}
