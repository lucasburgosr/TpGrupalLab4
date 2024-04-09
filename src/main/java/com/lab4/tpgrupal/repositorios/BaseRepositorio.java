package com.lab4.tpgrupal.repositorios;

import com.lab4.tpgrupal.entidades.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepositorio <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {

}
