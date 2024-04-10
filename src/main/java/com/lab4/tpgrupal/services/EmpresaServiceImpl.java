package com.lab4.tpgrupal.services;

import com.lab4.tpgrupal.entities.Empresa;
import com.lab4.tpgrupal.repositories.BaseRepository;
import com.lab4.tpgrupal.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl extends BaseServiceImpl<Empresa, Integer> implements EmpresaService {

    @Autowired
    private EmpresaRepository autorRepository;

    public EmpresaServiceImpl(BaseRepository<Empresa, Integer> baseRepository) {
        super(baseRepository);
    }
}
