package com.prueba.tecnica.canvia.admincore.services.impl;

import com.prueba.tecnica.canvia.admincore.models.Factura;
import com.prueba.tecnica.canvia.admincore.repo.IFacturaRepo;
import com.prueba.tecnica.canvia.admincore.repo.IGenericRepo;
import com.prueba.tecnica.canvia.admincore.services.interfaces.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl extends CrudGenericServiceImpl<Factura, Integer> implements IFacturaService {

    @Autowired
    private IFacturaRepo repo;

    @Override
    protected IGenericRepo<Factura, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Factura> listarPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
