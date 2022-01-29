package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.models.Cliente;
import com.prueba.tecnica.canvia.admincore.repo.IClienteRepo;
import com.prueba.tecnica.canvia.admincore.repo.IGenericRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends CrudGenericServiceImpl<Cliente, Integer> implements IClienteService {

    @Autowired
    private IClienteRepo repo;

    @Override
    protected IGenericRepo<Cliente, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Cliente> listarPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
