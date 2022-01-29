package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.models.ClienteFactura;
import com.prueba.tecnica.canvia.admincore.repo.IClienteFacturaRepo;
import com.prueba.tecnica.canvia.admincore.repo.IGenericRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacturaServiceImpl extends CrudGenericServiceImpl<ClienteFactura, Integer>
        implements IClienteFacturaService {

    @Autowired
    private IClienteFacturaRepo repo;

    @Override
    protected IGenericRepo<ClienteFactura, Integer> getRepo() {
        return repo;
    }
}
