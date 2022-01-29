package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.repo.IFacturaDetalleRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IFacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaDetalleServiceImpl implements IFacturaDetalleService {

    @Autowired
    private IFacturaDetalleRepo repo;

    @Override
    public Integer registrar(Integer id_factura, Integer id_item, Integer cantidad) {
        return repo.registrar(id_factura, id_item, cantidad);
    }
}
