package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.repo.IClienteFacturaRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IClienteFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacturaServiceImpl implements IClienteFacturaService {

    @Autowired
    private IClienteFacturaRepo repo;

    @Override
    public Integer registrar(Integer idCliente, Integer idFactura) {
        return repo.registrar(idCliente, idFactura);
    }
}
