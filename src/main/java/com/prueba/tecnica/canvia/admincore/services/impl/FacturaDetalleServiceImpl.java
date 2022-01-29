package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.models.FacturaDetalle;
import com.prueba.tecnica.canvia.admincore.repo.IFacturaDetalleRepo;
import com.prueba.tecnica.canvia.admincore.repo.IGenericRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IFacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaDetalleServiceImpl extends CrudGenericServiceImpl<FacturaDetalle, Integer>
        implements IFacturaDetalleService {

    @Autowired
    private IFacturaDetalleRepo repo;

    @Override
    protected IGenericRepo<FacturaDetalle, Integer> getRepo() {
        return repo;
    }
}
